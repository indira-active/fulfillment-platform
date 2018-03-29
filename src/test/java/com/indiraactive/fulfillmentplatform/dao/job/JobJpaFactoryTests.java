package com.indiraactive.fulfillmentplatform.dao.job;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.domain.Job;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobJpaFactoryTests {
    @Autowired
    private JobJpaFactory jobJpaFactory;


    @Test
    public void createJobJpaFromJob() {
        Integer expectedId = 1;
        Supplier expectedSupplier = new Supplier();
        String expectedCreatedByUser = "user";
        Date expectedStartDateTime = new Date();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";
        Job expectedJob = new Job(expectedId, expectedSupplier, expectedCreatedByUser, expectedStartDateTime, expectedRunOnce, expectedCronExpression);

        JobJpa actualJobJpa = jobJpaFactory.createJobJpa(expectedJob);

        assertEquals(expectedId, actualJobJpa.getId());
        assertEquals(expectedSupplier, actualJobJpa.getSupplier());
        assertEquals(expectedCreatedByUser, actualJobJpa.getCreatedByUser());
        assertEquals(expectedStartDateTime, actualJobJpa.getStartDateTime());
        assertEquals(expectedRunOnce, actualJobJpa.isRunOnce());
        assertEquals(expectedCronExpression, actualJobJpa.getCronExpression());
    }

    @Test
    public void createJobJpaFromJobWithNoId() {
        Supplier expectedSupplier = new Supplier();
        String expectedCreatedByUser = "user";
        Date expectedStartDateTime = new Date();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";
        Job expectedJob = new Job(expectedSupplier, expectedCreatedByUser, expectedStartDateTime, expectedRunOnce, expectedCronExpression);

        JobJpa actualJobJpa = jobJpaFactory.createJobJpa(expectedJob);

        assertNull(actualJobJpa.getId());
        assertEquals(expectedSupplier, actualJobJpa.getSupplier());
        assertEquals(expectedCreatedByUser, actualJobJpa.getCreatedByUser());
        assertEquals(expectedStartDateTime, actualJobJpa.getStartDateTime());
        assertEquals(expectedRunOnce, actualJobJpa.isRunOnce());
        assertEquals(expectedCronExpression, actualJobJpa.getCronExpression());
    }

}
