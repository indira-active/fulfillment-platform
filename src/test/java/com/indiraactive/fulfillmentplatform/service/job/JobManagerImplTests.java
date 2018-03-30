package com.indiraactive.fulfillmentplatform.service.job;

import com.indiraactive.fulfillmentplatform.dao.job.JobDao;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpaFactory;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.domain.Job;
import com.indiraactive.fulfillmentplatform.domain.JobFactory;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobManagerImplTests {
    @Autowired
    private JobManagerImpl jobManager;

    @Autowired
    private JobJpaFactory jobJpaFactory;

    @Autowired
    private CalendarSync calendarSync;

    @MockBean
    private JobFactory mockJobFactory;

    @MockBean
    private JobDao mockJobDao;

    @MockBean
    private SupplierRepository mockSupplierRepository;

    @Captor
    private ArgumentCaptor<JobJpa> jobJpaArgumentCaptor;

    @Test
    public void testAddJob(){
        Job expectedJob = new Job(1, "createdBy", calendarSync.getLocalDateTimeNow(),
                false, "******");
        JobJpa expectedJobJpa = jobJpaFactory.createJobJpaNoId(expectedJob);
        JobJpa savedJobJpa = new JobJpa();
        savedJobJpa.setId(1);
        Mockito.when(mockJobFactory.createJob(savedJobJpa)).thenReturn(expectedJob);
        Mockito.when(mockJobDao.save(expectedJobJpa)).thenReturn(savedJobJpa);
        //Mockito.when(mockSupplierRepository.findOne(expectedJob.getSupplierId())).thenReturn()

        Job actualJob = jobManager.saveJob(expectedJob);
        Mockito.verify(mockJobDao).save(jobJpaArgumentCaptor.capture());

        assertEquals(expectedJobJpa, jobJpaArgumentCaptor.getValue());
        assertEquals(expectedJob, actualJob);
    }
}