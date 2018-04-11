package com.indiraactive.fulfillmentplatform.controller;

import com.indiraactive.fulfillmentplatform.config.SecurityConfig;
import com.indiraactive.fulfillmentplatform.domain.Job;
import com.indiraactive.fulfillmentplatform.service.job.JobManager;
import com.indiraactive.fulfillmentplatform.service.jobQueue.JobQueueManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(JobController.class)
public class JobControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecurityConfig securityConfig;

    @MockBean
    private JobManager jobManager;

    @MockBean
    private JobQueueManager jobQueueManager;

    @Captor
    private ArgumentCaptor<Job> jobArgumentCaptor;

    @Test
    @WithMockUser
    public void testGetJobIndex() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/job"))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("job"))
                .andExpect(MockMvcResultMatchers.view().name("job"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void testAddJob() throws Exception {
        Job jobToAdd = new Job(1, "createdBy", LocalDateTime.now(), false, "******", true);

        mockMvc.perform(MockMvcRequestBuilders.post("/job/add")
                .flashAttr("job",jobToAdd))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(jobManager).saveJob(jobArgumentCaptor.capture());

        assertEquals(jobToAdd, jobArgumentCaptor.getValue());
    }

    @Test
    public void testGetJobIndexNoAuth() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/job"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void testAddJobNoAuth() throws Exception {
        Job jobToAdd = new Job(1, "createdBy", LocalDateTime.now(), false, "******", true);

        mockMvc.perform(MockMvcRequestBuilders.post("/job/add")
                .flashAttr("job",jobToAdd))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
