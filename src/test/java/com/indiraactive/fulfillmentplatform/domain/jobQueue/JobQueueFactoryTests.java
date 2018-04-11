package com.indiraactive.fulfillmentplatform.domain.jobQueue;

import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueDao;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueJpa;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueJpaFactory;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobQueueFactoryTests {
    @Autowired
    private JobQueueFactory jobQueueFactory;

    @Autowired
    private CalendarSync calendarSync;

    @Test
    public void createJobQueueFromJobQueueJpaWithId() {
        JobQueueJpa jobQueueJpa;
        Integer expectedId = 1;
        JobJpa expectedJobJpa = new JobJpa();
        Integer expectedJobId = 2;
        expectedJobJpa.setId(expectedJobId);
        Date expectedExecuteDateTime = calendarSync.getDateNow();
        boolean expectedExecuted = false;
        jobQueueJpa = new JobQueueJpa(expectedId, expectedJobJpa, expectedExecuteDateTime, expectedExecuted);

        JobQueue actualJobQueue = jobQueueFactory.createJobQueueWithId(jobQueueJpa);

        assertNotNull(actualJobQueue);
        assertEquals(expectedId, actualJobQueue.getId());
        assertEquals(expectedJobId, actualJobQueue.getJobId());
        assertEquals(expectedExecuteDateTime, calendarSync.getDateFromLocalDateTime(actualJobQueue.getExecuteDateTime()));
        assertEquals(expectedExecuted, actualJobQueue.isExecuted());
    }

    @Test
    public void createJobQueueFromJobQueueJpaWithNoId() {
        JobQueueJpa jobQueueJpa;
        JobJpa expectedJobJpa = new JobJpa();
        Integer expectedJobId = 2;
        expectedJobJpa.setId(expectedJobId);
        Date expectedExecuteDateTime = calendarSync.getDateNow();
        boolean expectedExecuted = false;
        jobQueueJpa = new JobQueueJpa(expectedJobJpa, expectedExecuteDateTime, expectedExecuted);

        JobQueue actualJobQueue = jobQueueFactory.createJobQueueWithId(jobQueueJpa);

        assertNotNull(actualJobQueue);
        assertEquals(expectedJobId, actualJobQueue.getJobId());
        assertEquals(expectedExecuteDateTime, calendarSync.getDateFromLocalDateTime(actualJobQueue.getExecuteDateTime()));
        assertEquals(expectedExecuted, actualJobQueue.isExecuted());
    }
}
