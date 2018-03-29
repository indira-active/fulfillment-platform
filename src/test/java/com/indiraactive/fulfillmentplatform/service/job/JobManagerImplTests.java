package com.indiraactive.fulfillmentplatform.service.job;

import com.indiraactive.fulfillmentplatform.dao.job.JobDao;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.job.JobJpaFactory;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.domain.Job;
import com.indiraactive.fulfillmentplatform.domain.JobFactory;
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

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobManagerImplTests {
    @Autowired
    private JobManagerImpl jobManager;

    @Autowired
    private JobJpaFactory jobJpaFactory;

    @MockBean
    private JobFactory jobFactory;

    @MockBean
    private JobDao mockJobDao;

    @Captor
    private ArgumentCaptor<JobJpa> jobJpaArgumentCaptor;

    @Test
    public void testAddJob(){
        Job expectedJob = new Job(new Supplier(), "createdBy", new Date(),
                false, "******");
        JobJpa expectedJobJpa = jobJpaFactory.createJobJpaNoId(expectedJob);
        JobJpa savedJobJpa = new JobJpa();
        savedJobJpa.setId(1);
        Mockito.when(mockJobDao.save(expectedJobJpa)).thenReturn(savedJobJpa);
        Mockito.when(jobFactory.createJob(savedJobJpa)).thenReturn(expectedJob);

        Job actualJob = jobManager.saveJob(expectedJob);
        Mockito.verify(mockJobDao).save(jobJpaArgumentCaptor.capture());

        assertEquals(expectedJobJpa, jobJpaArgumentCaptor.getValue());
        assertEquals(expectedJob, actualJob);
    }
}
