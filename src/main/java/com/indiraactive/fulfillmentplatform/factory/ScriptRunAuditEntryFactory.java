package com.indiraactive.fulfillmentplatform.factory;

import com.indiraactive.fulfillmentplatform.model.db.ScriptRunAuditEntry;

/**
 * Responsible for producing object that relate to the ScriptRunAuditEntry data model
 */
public class ScriptRunAuditEntryFactory {
    /**
     * Produces a ScriptRunAuditEntry object given the properties are provided that define it.
     * @param supplierId Id of the supplier that is being audited
     * @param startDateTime The time the script started running in epoch
     * @param endDateTime The time the script stopped running in epoch
     * @param userTriggered
     * @param successCode
     * @return
     */
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
