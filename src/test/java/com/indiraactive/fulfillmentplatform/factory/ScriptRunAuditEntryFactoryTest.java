package com.indiraactive.fulfillmentplatform.factory;

import com.indiraactive.fulfillmentplatform.model.db.ScriptRunAuditEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.font.ScriptRun;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScriptRunAuditEntryFactoryTest {

    @Test
    public void testCreateScriptRunAuditEntry() {
        Integer expectedSupplierId = 123;
        long expectedStartDateTime = 1521502000000L;
        long expectedEndDateTime = 1521502003000L;
        String expectedUserTriggered = "userTested";
        String expectedSuccessCode = "SUCCESS";

        ScriptRunAuditEntry actualScriptRunAuditEntry = ScriptRunAuditEntryFactory.CreateScriptRunAuditEntry(expectedSupplierId, expectedStartDateTime, expectedEndDateTime, expectedUserTriggered, expectedSuccessCode);

        assertEquals(expectedSupplierId, actualScriptRunAuditEntry.getSupplierId());
        assertEquals(expectedStartDateTime, actualScriptRunAuditEntry.getStartDateTime());
        assertEquals(expectedEndDateTime, actualScriptRunAuditEntry.getFinishDateTime());
        assertEquals(expectedUserTriggered, actualScriptRunAuditEntry.getUserTriggered());
        assertEquals(expectedSuccessCode, actualScriptRunAuditEntry.getSuccessCode());
    }

}