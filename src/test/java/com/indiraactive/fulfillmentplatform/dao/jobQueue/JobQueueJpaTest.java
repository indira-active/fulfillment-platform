package com.indiraactive.fulfillmentplatform.dao.jobQueue;

import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
public class JobQueueJpaTest {
    @Test
    public void testJobQueueJpa() {
        JobQueueJpa actualJobQueueJpa = new JobQueueJpa();
        Integer expectedId = 1;
        JobJpa expectedJobJpa = new JobJpa(1, new Supplier(), "createdBy", new Date(), false, "*****", true);
        Date expectedExecuteDateTime= new Date();
        Boolean expectedIsExecuted = false;

        actualJobQueueJpa.setId(expectedId);
        actualJobQueueJpa.setJobJpa(expectedJobJpa);
        actualJobQueueJpa.setExecuteDateTime(expectedExecuteDateTime);
        actualJobQueueJpa.setExecuted(expectedIsExecuted);

        assertEquals(expectedId, actualJobQueueJpa.getId());
        assertEquals(expectedJobJpa, actualJobQueueJpa.getJobJpa());
        assertEquals(expectedExecuteDateTime, actualJobQueueJpa.getExecuteDateTime());
        assertEquals(expectedIsExecuted, actualJobQueueJpa.isExecuted());
    }

    @Test
    public void testJobQueueJpaConstructorWithoutId() {
        Integer expectedId = 1;
        JobJpa expectedJobJpa = new JobJpa(1, new Supplier(), "createdBy", new Date(), false, "*****", true);
        Date expectedExecuteDateTime= new Date();
        Boolean expectedIsExecuted = false;

        JobQueueJpa actualJobQueueJpa = new JobQueueJpa(expectedId, expectedJobJpa, expectedExecuteDateTime, expectedIsExecuted);

        assertEquals(expectedId, actualJobQueueJpa.getId());
        assertEquals(expectedJobJpa, actualJobQueueJpa.getJobJpa());
        assertEquals(expectedExecuteDateTime, actualJobQueueJpa.getExecuteDateTime());
        assertEquals(expectedIsExecuted, actualJobQueueJpa.isExecuted());
    }

    @Test
    public void testJobQueueJpaConstructorWithId() {
        JobJpa expectedJobJpa = new JobJpa(1, new Supplier(), "createdBy", new Date(), false, "*****", true);
        Date expectedExecuteDateTime= new Date();
        Boolean expectedIsExecuted = false;

        JobQueueJpa actualJobQueueJpa = new JobQueueJpa(expectedJobJpa, expectedExecuteDateTime, expectedIsExecuted);

        assertNull(actualJobQueueJpa.getId());
        assertEquals(expectedJobJpa, actualJobQueueJpa.getJobJpa());
        assertEquals(expectedExecuteDateTime, actualJobQueueJpa.getExecuteDateTime());
        assertEquals(expectedIsExecuted, actualJobQueueJpa.isExecuted());
    }
}
