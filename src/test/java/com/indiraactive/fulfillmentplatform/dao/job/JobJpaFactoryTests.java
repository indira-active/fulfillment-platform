package com.indiraactive.fulfillmentplatform.dao.job;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.domain.Job;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobJpaFactoryTests {
    @Autowired
    private JobJpaFactory jobJpaFactory;

    @Autowired
    private CalendarSync calendarSync;

    @MockBean
    private SupplierRepository supplierRepository;

    @Test
    public void createJobJpaFromJob() {
        Integer expectedId = 1;
        Supplier expectedSupplier = new Supplier();
        expectedSupplier.setSupplierId(2);
        String expectedCreatedByUser = "user";
        LocalDateTime expectedStartDateTime = calendarSync.getLocalDateTimeNow();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";
        boolean expectedActive = false;
        Job expectedJob = new Job(expectedId, expectedSupplier.getSupplierId(), expectedCreatedByUser, expectedStartDateTime, expectedRunOnce, expectedCronExpression, expectedActive);
        Mockito.when(supplierRepository.findOne(expectedSupplier.getSupplierId())).thenReturn(expectedSupplier);

        JobJpa actualJobJpa = jobJpaFactory.createJobJpa(expectedJob);

        assertEquals(expectedId, actualJobJpa.getId());
        assertEquals(expectedSupplier, actualJobJpa.getSupplier());
        assertEquals(expectedCreatedByUser, actualJobJpa.getCreatedByUser());
        assertEquals(expectedStartDateTime, calendarSync.getLocalDateTimeFromDate(actualJobJpa.getStartDateTime()));
        assertEquals(expectedRunOnce, actualJobJpa.isRunOnce());
        assertEquals(expectedCronExpression, actualJobJpa.getCronExpression());
        assertEquals(expectedActive, actualJobJpa.isActive());
    }

    @Test
    public void createJobJpaFromJobWithNoId() {
        Supplier expectedSupplier = new Supplier();
        expectedSupplier.setSupplierId(2);
        String expectedCreatedByUser = "user";
        LocalDateTime expectedStartDateTime = calendarSync.getLocalDateTimeNow();
        boolean expectedRunOnce = true;
        String expectedCronExpression = "* * * * * *";
        boolean expectedActive = false;
        Job expectedJob = new Job(expectedSupplier.getSupplierId(), expectedCreatedByUser, expectedStartDateTime, expectedRunOnce, expectedCronExpression, expectedActive);
        Mockito.when(supplierRepository.findOne(expectedSupplier.getSupplierId())).thenReturn(expectedSupplier);

        JobJpa actualJobJpa = jobJpaFactory.createJobJpa(expectedJob);

        assertNull(actualJobJpa.getId());
        assertEquals(expectedSupplier, actualJobJpa.getSupplier());
        assertEquals(expectedCreatedByUser, actualJobJpa.getCreatedByUser());
        assertEquals(calendarSync.getDateFromLocalDateTime(expectedStartDateTime), actualJobJpa.getStartDateTime());
        assertEquals(expectedRunOnce, actualJobJpa.isRunOnce());
        assertEquals(expectedCronExpression, actualJobJpa.getCronExpression());
        assertEquals(expectedActive, actualJobJpa.isActive());
    }
}
