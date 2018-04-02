package com.indiraactive.fulfillmentplatform.domain.jobQueue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
public class JobQueueTests {
    @Test
    public void testJobQueue() {
        JobQueue actualJobQueue = new JobQueue();
        Integer expectedId = 1;
        Integer expectedJobJpaId = 2;
        LocalDateTime expectedExecuteDateTime = LocalDateTime.now();
        Boolean expectedIsExecuted = false;

        actualJobQueue.setId(expectedId);
        actualJobQueue.setJobId(expectedJobJpaId);
        actualJobQueue.setExecuteDateTime(expectedExecuteDateTime);
        actualJobQueue.setExecuted(expectedIsExecuted);

        assertEquals(expectedId, actualJobQueue.getId());
        assertEquals(expectedJobJpaId, actualJobQueue.getJobId());
        assertEquals(expectedExecuteDateTime, actualJobQueue.getExecuteDateTime());
        assertEquals(expectedIsExecuted, actualJobQueue.isExecuted());
    }

    @Test
    public void testJobQueueConstructorWithoutId() {
        Integer expectedId = 1;
        Integer expectedJobJpaId = 2;
        LocalDateTime expectedExecuteDateTime = LocalDateTime.now();
        Boolean expectedIsExecuted = false;

        JobQueue actualJobQueue = new JobQueue(expectedId, expectedJobJpaId, expectedExecuteDateTime, expectedIsExecuted);

        assertEquals(expectedId, actualJobQueue.getId());
        assertEquals(expectedJobJpaId, actualJobQueue.getJobId());
        assertEquals(expectedExecuteDateTime, actualJobQueue.getExecuteDateTime());
        assertEquals(expectedIsExecuted, actualJobQueue.isExecuted());
    }

    @Test
    public void testJobQueueConstructorWithId() {
        Integer expectedJobJpaId = 2;
        LocalDateTime expectedExecuteDateTime = LocalDateTime.now();
        Boolean expectedIsExecuted = false;

        JobQueue actualJobQueue = new JobQueue(expectedJobJpaId, expectedExecuteDateTime, expectedIsExecuted);

        assertNull(actualJobQueue.getId());
        assertEquals(expectedJobJpaId, actualJobQueue.getJobId());
        assertEquals(expectedExecuteDateTime, actualJobQueue.getExecuteDateTime());
        assertEquals(expectedIsExecuted, actualJobQueue.isExecuted());
    }
}
