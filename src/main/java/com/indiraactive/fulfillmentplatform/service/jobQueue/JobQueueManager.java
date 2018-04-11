package com.indiraactive.fulfillmentplatform.service.jobQueue;

import com.indiraactive.fulfillmentplatform.domain.jobQueue.JobQueue;

import java.util.List;

public interface JobQueueManager {
    JobQueue queueJob(JobQueue queuedJob);

    /**
     * Retrieves all jobs that have not been executed yet
     * @return List of jobs that have not been executed yet
     */
    List<JobQueue> getJobsQueued();
}
