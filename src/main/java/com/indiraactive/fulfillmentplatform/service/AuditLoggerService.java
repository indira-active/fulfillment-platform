package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dal.ScriptRunAuditEntryRepository;
import com.indiraactive.fulfillmentplatform.factory.ScriptRunAuditEntryFactory;
import com.indiraactive.fulfillmentplatform.model.db.ScriptRunAuditEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Logs data to the database
 */
@Component
public class AuditLoggerService {
    /**
     * Database abstraction that will hold logs
     */
    @Autowired
    private ScriptRunAuditEntryRepository scriptRunAuditEntryRepository;

    /**
     * This method will log when the inventory updater script has been run
     * and will log to the database information about the run.
     * @param supplierId Supplier id value to log
     * @param startDateTime start date time to log
     * @param endDateTime end date time to log
     * @param userTriggered user triggered to log
     * @param successCode success code after run of script
     * @return Whether or not the log was successfully saved to the database
     */
    public boolean logScriptRun(Integer supplierId, long startDateTime, long endDateTime, String userTriggered, String successCode) {
        ScriptRunAuditEntry scriptRunAuditEntry = ScriptRunAuditEntryFactory.CreateScriptRunAuditEntry(supplierId, startDateTime, endDateTime, userTriggered, successCode);


        scriptRunAuditEntryRepository.save(scriptRunAuditEntry);
        return false;
    }
}