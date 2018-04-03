package com.indiraactive.fulfillmentplatform.service.jobQueue;

import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueDao;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueJpa;
import com.indiraactive.fulfillmentplatform.dao.jobQueue.JobQueueJpaFactory;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.domain.jobQueue.JobQueue;
import com.indiraactive.fulfillmentplatform.domain.jobQueue.JobQueueFactory;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobQueueManagerImplTests {
    @Autowired
    private JobQueueManagerImpl jobQueueManager;

    @Autowired
    private CalendarSync calendarSync;

    @MockBean
    private JobQueueFactory jobQueueFactory;

    @MockBean
    private JobQueueDao jobQueueDao;

    @MockBean
    private JobQueueJpaFactory jobQueueJpaFactory;

    @Captor
    private ArgumentCaptor<JobQueueJpa> jobQueueJpaArgumentCaptor;

    @Test
    public void testQueueJob() {
        JobQueue jobToQueue;
        Integer expectedJobId = 2;
        LocalDateTime expectedExecuteTime = calendarSync.getLocalDateTimeNow();
        boolean expectedExecuted = false;
        jobToQueue = new JobQueue(expectedJobId, expectedExecuteTime, expectedExecuted);
        JobQueueJpa toSave = new JobQueueJpa();
        Mockito.when(jobQueueJpaFactory.createJobQueueJpaWithoutId(jobToQueue)).thenReturn(toSave);


        jobQueueManager.queueJob(jobToQueue);
        Mockito.verify(jobQueueDao).save(jobQueueJpaArgumentCaptor.capture());

        assertEquals(toSave, jobQueueJpaArgumentCaptor.getValue());
    }

    @Test
    public void testGetJobsQueued() {
        JobJpa jobJpa1 = new JobJpa(1, new Supplier(), "user", calendarSync.getDateNow(), false, "***4**", true);
        JobJpa jobJpa2 = new JobJpa(2, new Supplier(), "user2", calendarSync.getDateNow(), false, "***3**", true);
        JobQueueJpa jobQueueJpa1 = new JobQueueJpa(1, jobJpa1, calendarSync.getDateNow(), false);
        JobQueueJpa jobQueueJpa2 = new JobQueueJpa(2, jobJpa2, calendarSync.getDateNow(), false);
        List<JobQueueJpa> jobQueueJpaList = new LinkedList<>();
        jobQueueJpaList.add(jobQueueJpa1);
        jobQueueJpaList.add(jobQueueJpa2);
        Mockito.when(jobQueueDao.findByExecutedOrderByExecuteDateTime(false)).thenReturn(jobQueueJpaList);
        JobQueue expectedJobQueue1 = new JobQueue();
        JobQueue expectedJobQueue2 = new JobQueue();
        Mockito.when(jobQueueFactory.createJobQueueWithId(jobQueueJpa1)).thenReturn(expectedJobQueue1);
        Mockito.when(jobQueueFactory.createJobQueueWithId(jobQueueJpa2)).thenReturn(expectedJobQueue2);

        List<JobQueue> actualJobsQueued = jobQueueManager.getJobsQueued();

        assertNotNull(actualJobsQueued);
        assertEquals(expectedJobQueue1, actualJobsQueued.get(0));
        assertEquals(expectedJobQueue2, actualJobsQueued.get(1));
    }
}
