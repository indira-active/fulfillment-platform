package com.indiraactive.fulfillmentplatform.domain;

import com.indiraactive.fulfillmentplatform.Application;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobFactoryTests {
    @Autowired
    private JobFactory jobFactory;

    @Test
    public void createJobFromValues() {
        Supplier expectedSupplier = new Supplier();
        String expectedCreatedByUser = "user";
        Date expectedStartDateTime = new Date();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";

        Job actualJob = jobFactory.createJob(expectedSupplier, expectedCreatedByUser, expectedStartDateTime, expectedRunOnce, expectedCronExpression);

        assertNull(actualJob.getId());
        assertEquals(expectedSupplier, actualJob.getSupplier());
        assertEquals(expectedCreatedByUser, actualJob.getCreatedByUser());
        assertEquals(expectedStartDateTime, actualJob.getStartDateTime());
        assertEquals(expectedRunOnce, actualJob.isRunOnce());
        assertEquals(expectedCronExpression, actualJob.getCronExpression());
    }

    @Test
    public void createJobFromJpa() {
        Integer expectedId = 1;
        Supplier expectedSupplier = new Supplier();
        String expectedCreatedByUser = "user";
        Date expectedStartDateTime = new Date();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";
        JobJpa expectedJobJpa = new JobJpa(expectedId, expectedSupplier, expectedCreatedByUser, expectedStartDateTime, expectedRunOnce, expectedCronExpression);

        Job actualJob = jobFactory.createJob(expectedJobJpa);

        assertEquals(expectedId, actualJob.getId());
        assertEquals(expectedSupplier, actualJob.getSupplier());
        assertEquals(expectedCreatedByUser, actualJob.getCreatedByUser());
        assertEquals(expectedStartDateTime, actualJob.getStartDateTime());
        assertEquals(expectedRunOnce, actualJob.isRunOnce());
        assertEquals(expectedCronExpression, actualJob.getCronExpression());
    }
}
