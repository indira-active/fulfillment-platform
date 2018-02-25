package com.indiraactive.fulfillmentplatform.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.indiraactive.fulfillmentplatform.service.ScriptPathFinder.ScriptName;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "inventoryUpdaterScriptPath = fake/path" })
public class ScriptPathFinderTests {
    @Autowired
    private ScriptPathFinder pathFinder;

    @Test
    public void testGetPathForSyncInventory() {
        ScriptName syncInventory = ScriptName.SYNC_INVENTORY;
        String expect = "fake/path";
        String actual = "";

        try {
            actual = pathFinder.getPath(syncInventory);
        } catch (Exception e ) {
            fail("Getting path for " + syncInventory + " was not successful, an exception was thrown.");
        }

        assertEquals(expect, actual);
    }
}
