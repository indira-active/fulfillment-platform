package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dao.job.JobDao;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueDao;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueJpa;
import com.indiraactive.fulfillmentplatform.service.inventoryUpdater.InventoryUpdater;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Contains any methods that are schedule based
 */
@Component
public class SchedulerImpl {
    @Autowired
    private JobDao jobDao;

    @Autowired
    private JobQueueDao jobQueueDao;

    @Autowired
    private CalendarSync calendarSync;

    @Autowired
    private InventoryUpdater inventoryUpdater;

    /**
     * Checks to see if sync_inventory needs to be ran on a schedule. This schedule
     * has yet to be determined.
     */
    @Scheduled(fixedRate = 5000)
    public void checkForJobs() throws Exception {
        System.out.println("Updating Job Queue");
        updateJobs();
        //System.out.println("Checking Job Queue For Pending Jobs");
        runJobs();
    }

    public void updateJobs() {
        List<JobJpa> jobsToCheckForUpdate = jobDao.findByStartDateTimeBeforeAndActive(calendarSync.getDateNow(),true); // TODO: Minute is not inclusive, delayed a minute
        System.out.println("Found " + jobsToCheckForUpdate.size() + " jobs that are candidates to schedule");
        for (JobJpa job : jobsToCheckForUpdate) {
            System.out.println("Checking to see if the following job needs to be queued, job id: "+ job.getId());
            if (!isJobQueued(job)) {
                System.out.println("Queuing the following job, job id: "+ job.getId());
                scheduleJob(job);
            }
            System.out.println("Checking to see if job id: "+ job.getId() + " is a run once job.");
            if(job.isRunOnce()) {
                System.out.println("Job is run once, turning off job id: "+ job.getId());
                turnOffJob(job);
            }
        }
    }

    private void turnOffJob(JobJpa job) {
        job.setActive(false);
        jobDao.save(job);
    }

    public void scheduleJob(JobJpa jobToSchedule) {
        Date executeDateTime = getExecuteDateTime(jobToSchedule);
        JobQueueJpa jobToAddToQueue = new JobQueueJpa(jobToSchedule, executeDateTime, false);
        jobQueueDao.save(jobToAddToQueue);
    }

    public boolean isJobQueued(JobJpa jobToSchedule) {
        JobQueueJpa queuedJob = jobQueueDao.findByExecutedAndJobJpa(false, jobToSchedule);
        return queuedJob != null;
    }

    public Date getExecuteDateTime(JobJpa jobToSchedule) {
        return calendarSync.getNextDateFromCronSequence(jobToSchedule.getCronExpression());
    }

    public void runJobs() {
        List<JobQueueJpa> candidateJobs = jobQueueDao.
                findByExecutedOrderByExecuteDateTime(false);
        System.out.println("Found " + candidateJobs.size() + " job(s) queued.");
        // if the current date time matches execute date time execute
        for (JobQueueJpa jobQueued : candidateJobs) {
            Date jobQueuedTime = jobQueued.getExecuteDateTime();
            Date dateTimeNow = calendarSync.getDateNow();
            System.out.println("Dates comparing for queued job jobQueueId: " + jobQueued.getId() + " executedDateTime: " + jobQueuedTime + " now: " + dateTimeNow);
            if (jobQueuedTime.before(dateTimeNow)) {
                Integer supplierId = jobQueued.getJobJpa().getSupplier().getSupplierId();
                try {
                    System.out.println("Attempting to execute the following jobQueueId: " + jobQueued.getId());
                    inventoryUpdater.updateInventory(supplierId);
                    jobQueued.setExecuted(true);
                    jobQueueDao.save(jobQueued);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
