package com.indiraactive.fulfillmentplatform.service.job;

import com.indiraactive.fulfillmentplatform.dao.job.JobDao;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpaFactory;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.domain.Job;
import com.indiraactive.fulfillmentplatform.domain.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class JobManagerImpl implements JobManager {
    @Autowired
    private JobDao jobDao;

    @Autowired
    private JobFactory jobFactory;

    @Autowired
    private JobJpaFactory jobJpaFactory;

    @Override
    public Job saveJob(Job jobWithNoId) {
        if (jobWithNoId.getCreatedByUser() == null) {
            //TODO: Pass user name to store once user management is finished
            jobWithNoId.setCreatedByUser("USER MANAGEMENT NOT IMPLEMENTED");
            System.out.println("USER MANAGEMENT NOT IMPLEMENTED");
        }
        JobJpa jobJpaToSave = jobJpaFactory.createJobJpaNoId(jobWithNoId);
        jobJpaToSave = jobDao.save(jobJpaToSave);

        return jobFactory.createJob(jobJpaToSave);
    }

    @Override
    public List<Job> getActiveJobs() {
        List<Job> activeJobs = new LinkedList<>();
        List<JobJpa> activeJobJpas = jobDao.findByActive(true);
        for (JobJpa jobJpa : activeJobJpas) {
            activeJobs.add(jobFactory.createJob(jobJpa));
        }

        return activeJobs;
    }

    @Override
    public Job getJobById(int jobId) {
        return jobFactory.createJob(jobDao.findOne(jobId));
    }

    @Override
    public boolean updateJob(Job job) {
        if (job.getSupplierId() == null) {
            System.out.println("Attempting to update job that does not exist");
            return false;
        }
        JobJpa updatedJobJpa = jobDao.save(jobJpaFactory.createJobJpa(job));
        return updatedJobJpa != null;
    }
}