package com.indiraactive.fulfillmentplatform.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIndexWithoutAuthorization() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    public void testInventoryUpdaterWithoutAuthoration() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/inventoryUpdater");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    public void testManageSuppliersWithoutAuthoration() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/manageSuppliers");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().is(302));
    }

    // TODO: Add tests for authorized requests
}