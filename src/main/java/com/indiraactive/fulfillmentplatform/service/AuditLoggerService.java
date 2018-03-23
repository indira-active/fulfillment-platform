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
     * Save a ScriptRunAuditEntry object to the database
     * @param scriptRunAuditEntry Object to save
     */
    public void logScriptRun(ScriptRunAuditEntry scriptRunAuditEntry) {
        scriptRunAuditEntryRepository.save(scriptRunAuditEntry);
    }
}