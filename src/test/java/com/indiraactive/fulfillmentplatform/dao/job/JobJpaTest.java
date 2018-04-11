package com.indiraactive.fulfillmentplatform.dao.job;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
public class JobJpaTest {
    @Test
    public void testJobJpa() {
        JobJpa jobJpa = new JobJpa();
        Integer expectedId = 123;
        Supplier expectedSupplier = new Supplier();
        String expectedUser = "user";
        Date expectedStartDateTime = new Date();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";

        jobJpa.setId(expectedId);
        jobJpa.setSupplier(expectedSupplier);
        jobJpa.setCreatedByUser(expectedUser);
        jobJpa.setStartDateTime(expectedStartDateTime);
        jobJpa.setRunOnce(expectedRunOnce);
        jobJpa.setCronExpression(expectedCronExpression);

        assertEquals(expectedId, jobJpa.getId());
        assertEquals(expectedSupplier, jobJpa.getSupplier());
        assertEquals(expectedUser, jobJpa.getCreatedByUser());
        assertEquals(expectedStartDateTime, jobJpa.getStartDateTime());
        assertEquals(expectedRunOnce, jobJpa.isRunOnce());
        assertEquals(expectedCronExpression, jobJpa.getCronExpression());
    }
}
