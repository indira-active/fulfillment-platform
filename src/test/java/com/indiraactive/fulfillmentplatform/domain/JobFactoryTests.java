package com.indiraactive.fulfillmentplatform.domain;

import com.indiraactive.fulfillmentplatform.Application;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
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

    @Autowired
    private CalendarSync calendarSync;

    @Test
    public void createJobFromValues() {
        Integer expectedSupplierId = 1;
        Supplier supplier = new Supplier();
        supplier.setSupplierId(expectedSupplierId);
        String expectedCreatedByUser = "user";
        Date expectedStartDateTime = new Date();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";
        boolean expectedActive = true;

        Job actualJob = jobFactory.createJob(supplier, expectedCreatedByUser, expectedStartDateTime, expectedRunOnce, expectedCronExpression, expectedActive);

        assertNull(actualJob.getId());
        assertEquals(expectedSupplierId, actualJob.getSupplierId());
        assertEquals(expectedCreatedByUser, actualJob.getCreatedByUser());
        assertEquals(calendarSync.getLocalDateTimeFromDate(expectedStartDateTime), actualJob.getStartDateTime());
        assertEquals(expectedRunOnce, actualJob.isRunOnce());
        assertEquals(expectedCronExpression, actualJob.getCronExpression());
        assertEquals(expectedActive, actualJob.isActive());
    }

    @Test
    public void createJobFromJpa() {
        Integer expectedId = 1;
        Supplier expectedSupplier = new Supplier();
        expectedSupplier.setSupplierId(2);
        String expectedCreatedByUser = "user";
        Date expectedStartDateTime = new Date();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";
        boolean expectedActive = true;
        JobJpa expectedJobJpa = new JobJpa(expectedId, expectedSupplier, expectedCreatedByUser,
                expectedStartDateTime, expectedRunOnce, expectedCronExpression, expectedActive);

        Job actualJob = jobFactory.createJob(expectedJobJpa);

        assertEquals(expectedId, actualJob.getId());
        assertEquals(expectedSupplier.getSupplierId(), actualJob.getSupplierId());
        assertEquals(expectedCreatedByUser, actualJob.getCreatedByUser());
        assertEquals(calendarSync.getLocalDateTimeFromDate(expectedStartDateTime), actualJob.getStartDateTime());
        assertEquals(expectedRunOnce, actualJob.isRunOnce());
        assertEquals(expectedCronExpression, actualJob.getCronExpression());
        assertEquals(expectedActive, actualJob.isActive());
    }
}
