package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import com.indiraactive.fulfillmentplatform.model.db.Supplier;
import com.indiraactive.fulfillmentplatform.utility.CommandLineRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.indiraactive.fulfillmentplatform.utility.CommandLineRunner.*;

import static org.mockito.BDDMockito.given;
import static org.junit.Assert.*;

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

    @Test
    public void testGetScriptPath() throws Exception {
        String expected = "\"path/to/script\"";
        given(this.scriptPathFinder.getPath(ScriptPathFinder.ScriptName.SYNC_INVENTORY)).willReturn(expected);

        String actual = inventoryUpdater.getInventoryUpdaterScriptPath();

        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateInventorySuccess() throws Exception{
        CompletionErrorType expected = CompletionErrorType.SUCCESS;
        int fakeSupplierId = 12;
        String commandToExecute = "python path ";
        given(this.supplierRepository.findOne(fakeSupplierId)).willReturn(new Supplier());
        given(this.scriptPathFinder.getPath(ScriptPathFinder.ScriptName.SYNC_INVENTORY)).willReturn("path");
        given(this.commandLineRunner.executeCommand(commandToExecute)).willReturn(CompletionErrorType.SUCCESS);

        CompletionErrorType actual = inventoryUpdater.updateInventory(fakeSupplierId);

        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateInventoryFailure() throws Exception{
        CompletionErrorType expected = CompletionErrorType.FAILURE;
        int fakeSupplierId = 12;
        String commandToExecute = "python path ";
        given(this.supplierRepository.findOne(fakeSupplierId)).willReturn(new Supplier());
        given(this.scriptPathFinder.getPath(ScriptPathFinder.ScriptName.SYNC_INVENTORY)).willReturn("path");
        given(this.commandLineRunner.executeCommand(commandToExecute)).willReturn(CompletionErrorType.FAILURE);

        CompletionErrorType actual = inventoryUpdater.updateInventory(fakeSupplierId);

        assertEquals(expected, actual);
    }
}
