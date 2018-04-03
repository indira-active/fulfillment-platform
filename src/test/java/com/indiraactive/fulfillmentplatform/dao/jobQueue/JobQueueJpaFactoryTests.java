package com.indiraactive.fulfillmentplatform.dao.jobQueue;

import com.indiraactive.fulfillmentplatform.dao.job.JobDao;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpaFactory;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.domain.Job;
import com.indiraactive.fulfillmentplatform.domain.jobQueue.JobQueue;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobQueueJpaFactoryTests {
    @Autowired
    private JobQueueJpaFactory jobQueueJpaFactory;

    @Autowired
    private CalendarSync calendarSync;

    @MockBean
    private JobDao jobDao;

    @Test
    public void createJobQueueJpaFromJobQueueWithId() {
        JobQueue jobQueue;
        Integer expectedId = 1;
        Integer expectedJobId = 2;
        LocalDateTime expectedExecuteDateTime = calendarSync.getLocalDateTimeNow();
        boolean expectedExecuted = false;
        jobQueue = new JobQueue(expectedId, expectedJobId, expectedExecuteDateTime, expectedExecuted);
        JobJpa expectedJobJpa = new JobJpa();
        expectedJobJpa.setId(expectedJobId);
        Mockito.when(jobDao.findOne(expectedJobId)).thenReturn(expectedJobJpa);

        JobQueueJpa actualJobQueueJpa = jobQueueJpaFactory.createJobQueueWithId(jobQueue);

        assertNotNull(actualJobQueueJpa);
        assertEquals(expectedId, actualJobQueueJpa.getId());
        assertEquals(expectedJobId, actualJobQueueJpa.getJobJpa().getId());
        assertEquals(expectedExecuteDateTime, calendarSync.getLocalDateTimeFromDate(actualJobQueueJpa.getExecuteDateTime()));
        assertEquals(expectedExecuted, actualJobQueueJpa.isExecuted());
    }

    @Test
    public void createJobQueueJpaFromJobQueueWithNoId() {
        JobQueue jobQueue;
        Integer expectedJobId = 2;
        LocalDateTime expectedExecuteDateTime = calendarSync.getLocalDateTimeNow();
        boolean expectedExecuted = false;
        jobQueue = new JobQueue(expectedJobId, expectedExecuteDateTime, expectedExecuted);
        JobJpa expectedJobJpa = new JobJpa();
        expectedJobJpa.setId(expectedJobId);
        Mockito.when(jobDao.findOne(expectedJobId)).thenReturn(expectedJobJpa);

        JobQueueJpa actualJobQueueJpa = jobQueueJpaFactory.createJobQueueWithId(jobQueue);

        assertNotNull(actualJobQueueJpa);
        assertEquals(expectedJobId, actualJobQueueJpa.getJobJpa().getId());
        assertEquals(expectedExecuteDateTime, calendarSync.getLocalDateTimeFromDate(actualJobQueueJpa.getExecuteDateTime()));
        assertEquals(expectedExecuted, actualJobQueueJpa.isExecuted());
    }
}
