package com.indiraactive.fulfillmentplatform.dao.jobQueue;

import com.indiraactive.fulfillmentplatform.dao.job.JobDao;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.domain.jobQueue.JobQueue;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
import org.hibernate.boot.jaxb.internal.stax.LocalSchemaLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JobQueueJpaFactory {
    @Autowired
    private JobDao jobDao;

    @Autowired
    private CalendarSync calendarSync;

    public JobQueueJpa createJobQueueWithId(JobQueue jobQueue) {
        JobJpa jobJpa = jobDao.findOne(jobQueue.getJobId());
        Date timeToBeExecuted = calendarSync.getDateFromLocalDateTime(jobQueue.getExecuteDateTime());
        return new JobQueueJpa(jobQueue.getId(), jobJpa, timeToBeExecuted, jobQueue.isExecuted());
    }

    public JobQueueJpa createJobQueueJpaWithoutId(JobQueue jobQueue) {
        JobJpa jobJpa = jobDao.findOne(jobQueue.getJobId());
        Date timeToBeExecuted = calendarSync.getDateFromLocalDateTime(jobQueue.getExecuteDateTime());
        return new JobQueueJpa(jobJpa, timeToBeExecuted, jobQueue.isExecuted());
    }
}
