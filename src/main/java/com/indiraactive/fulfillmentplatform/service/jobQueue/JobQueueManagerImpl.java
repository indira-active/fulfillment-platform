package com.indiraactive.fulfillmentplatform.service.jobQueue;

import com.indiraactive.fulfillmentplatform.dao.job.JobDao;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueDao;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueJpaFactory;
import com.indiraactive.fulfillmentplatform.domain.jobQueue.JobQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobQueueManagerImpl implements JobQueueManager {
    @Autowired
    private JobQueueDao jobQueueDao;

    @Autowired
    private JobQueueJpaFactory jobQueueJpaFacotry;

    @Override
    public JobQueue queueJob(JobQueue queuedJob) {
        return null;
    }

    @Override
    public List<JobQueue> getJobsQueued() {
        return null;
    }
}
