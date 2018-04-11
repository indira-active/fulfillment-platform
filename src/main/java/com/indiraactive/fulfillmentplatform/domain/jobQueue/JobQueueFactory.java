package com.indiraactive.fulfillmentplatform.domain.jobQueue;

import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueJpa;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JobQueueFactory {
    @Autowired
    private CalendarSync calendarSync;

    public JobQueue createJobQueueWithId(JobQueueJpa jobQueueJpaToConvert) {
        Integer id = jobQueueJpaToConvert.getId();
        Integer jobId = jobQueueJpaToConvert.getJobJpa().getId();
        LocalDateTime convertedExecuteTime = calendarSync.getLocalDateTimeFromDate(jobQueueJpaToConvert.getExecuteDateTime());
        return new JobQueue(id, jobId, convertedExecuteTime, jobQueueJpaToConvert.isExecuted());
    }

    public JobQueue createJobQueueJpaWithoutId(JobQueueJpa jobQueueJpaToConvert) {
        Integer jobId = jobQueueJpaToConvert.getJobJpa().getId();
        LocalDateTime convertedExecuteTime = calendarSync.getLocalDateTimeFromDate(jobQueueJpaToConvert.getExecuteDateTime());
        return new JobQueue(jobId, convertedExecuteTime, jobQueueJpaToConvert.isExecuted());
    }
}
