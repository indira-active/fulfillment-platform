package com.indiraactive.fulfillmentplatform.domain;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class JobTests {
    @Test
    public void testJob() {
        Job job = new Job();
        Integer expectedId = 123;
        Supplier expectedSupplier = new Supplier();
        String expectedUser = "user";
        Date expectedStartDateTime = new Date();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";

        job.setId(expectedId);
        job.setSupplier(expectedSupplier);
        job.setCreatedByUser(expectedUser);
        job.setStartDateTime(expectedStartDateTime);
        job.setRunOnce(expectedRunOnce);
        job.setCronExpression(expectedCronExpression);

        assertEquals(expectedId, job.getId());
        assertEquals(expectedSupplier, job.getSupplier());
        assertEquals(expectedUser, job.getCreatedByUser());
        assertEquals(expectedStartDateTime, job.getStartDateTime());
        assertEquals(expectedRunOnce, job.isRunOnce());
        assertEquals(expectedCronExpression, job.getCronExpression());
    }
}
