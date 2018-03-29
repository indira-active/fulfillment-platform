package com.indiraactive.fulfillmentplatform.service.job;

import com.indiraactive.fulfillmentplatform.dao.job.JobDao;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpaFactory;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.domain.Job;
import com.indiraactive.fulfillmentplatform.domain.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        JobJpa jobJpaToSave = jobJpaFactory.createJobJpaNoId(jobWithNoId);
        jobJpaToSave = jobDao.save(jobJpaToSave);

        return jobFactory.createJob(jobJpaToSave);
    }
}
