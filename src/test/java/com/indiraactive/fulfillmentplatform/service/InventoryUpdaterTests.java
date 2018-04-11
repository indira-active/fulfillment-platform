package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.service.inventoryUpdater.InventoryUpdater;
import com.indiraactive.fulfillmentplatform.utility.CommandLineRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryUpdaterTests {
    @MockBean
    private ScriptPathFinder scriptPathFinder;

    @MockBean
    private SupplierRepository supplierRepository;

    @MockBean
    private CommandLineRunner commandLineRunner;

    @Autowired
    private InventoryUpdater inventoryUpdater;

    private String pathToScript = "path";

    @Before
    public void setUpMocks() throws Exception {
        when(this.supplierRepository.findOne(1)).thenReturn(new Supplier());
        when(this.scriptPathFinder.getPath(ScriptPathFinder.ScriptName.SYNC_INVENTORY)).thenReturn(pathToScript);
    }



    @Test
    public void testGetScriptPath() throws Exception {
        assertEquals(pathToScript, inventoryUpdater.getInventoryUpdaterScriptPath());
    }

}