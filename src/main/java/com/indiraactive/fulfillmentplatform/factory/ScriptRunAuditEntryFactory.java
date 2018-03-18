package com.indiraactive.fulfillmentplatform.factory;

import com.indiraactive.fulfillmentplatform.model.db.ScriptRunAuditEntry;

public class ScriptRunAuditEntryFactory {
    public static ScriptRunAuditEntry CreateScriptRunAuditEntry(Integer supplierId, long startDateTime, long endDateTime, String userTriggered, String successCode) {
        ScriptRunAuditEntry scriptRunAuditEntry = new ScriptRunAuditEntry();

        scriptRunAuditEntry.setSupplierId(supplierId);
        scriptRunAuditEntry.setStartDateTime(startDateTime);
        scriptRunAuditEntry.setFinishDateTime(endDateTime);
        scriptRunAuditEntry.setUserTriggered(userTriggered);
        scriptRunAuditEntry.setSuccessCode(successCode);

        return scriptRunAuditEntry;
    }
}
