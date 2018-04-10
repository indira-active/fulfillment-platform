package com.indiraactive.fulfillmentplatform.service.job;

import com.indiraactive.fulfillmentplatform.domain.Job;

import java.util.List;

public interface JobManager {
    Job saveJob(Job job);
    List<Job> getActiveJobs();
}