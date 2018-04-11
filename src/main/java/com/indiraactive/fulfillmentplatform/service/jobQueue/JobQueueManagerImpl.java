package com.indiraactive.fulfillmentplatform.service.jobQueue;

import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueDao;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueJpa;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueJpaFactory;
import com.indiraactive.fulfillmentplatform.domain.jobQueue.JobQueue;
import com.indiraactive.fulfillmentplatform.domain.jobQueue.JobQueueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class JobQueueManagerImpl implements JobQueueManager {
    @Autowired
    private JobQueueDao jobQueueDao;

    @Autowired
    private JobQueueJpaFactory jobQueueJpaFactory;

    @Autowired
    private JobQueueFactory jobQueueFactory;

    @Override
    public JobQueue queueJob(JobQueue queuedJob) {
        if (queuedJob.getId() != null ) {
            System.out.println("Unable to queue previously queued job");
            return null;
        }

        JobQueueJpa jobQueueJpaToSave = jobQueueJpaFactory.createJobQueueJpaWithoutId(queuedJob);
        JobQueueJpa newQueuedJobQueueJpa = jobQueueDao.save(jobQueueJpaToSave);

        return jobQueueFactory.createJobQueueWithId(newQueuedJobQueueJpa);
    }

    @Override
    public List<JobQueue> getJobsQueued() {
        List<JobQueueJpa> jobsJpaQueued = jobQueueDao.findByExecutedOrderByExecuteDateTime(false);
        List<JobQueue> jobsQueued = new LinkedList<>();
        jobsJpaQueued.forEach(x -> jobsQueued.add(jobQueueFactory.createJobQueueWithId(x)));

        return jobsQueued;
    }
}
