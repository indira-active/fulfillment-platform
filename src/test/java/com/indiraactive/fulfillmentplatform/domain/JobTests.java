package com.indiraactive.fulfillmentplatform.domain;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class JobTests {
    @Test
    public void testJob() {
        Job job = new Job();
        Integer expectedId = 123;
        Integer expectedSupplier = 1;
        String expectedUser = "user";
        LocalDateTime expectedStartDateTime = LocalDateTime.now();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";

        job.setId(expectedId);
        job.setSupplierId(expectedSupplier);
        job.setCreatedByUser(expectedUser);
        job.setStartDateTime(expectedStartDateTime);
        job.setRunOnce(expectedRunOnce);
        job.setCronExpression(expectedCronExpression);

        assertEquals(expectedId, job.getId());
        assertEquals(expectedSupplier, job.getSupplierId());
        assertEquals(expectedUser, job.getCreatedByUser());
        assertEquals(expectedStartDateTime, job.getStartDateTime());
        assertEquals(expectedRunOnce, job.isRunOnce());
        assertEquals(expectedCronExpression, job.getCronExpression());
    }
}
