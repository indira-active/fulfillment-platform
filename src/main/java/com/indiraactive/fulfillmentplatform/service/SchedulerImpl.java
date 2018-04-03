package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dao.scheduledTask.ScheduledTaskRepository;
import com.indiraactive.fulfillmentplatform.dao.scriptRunAuditEntry.ScriptRunAuditEntryRepository;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.dao.scheduledTask.ScheduledTaskJpa;
import com.indiraactive.fulfillmentplatform.dao.scheduledTask.ScheduledTaskRunDaysJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Contains any methods that are schedule based
 */
@Component
public class SchedulerImpl {
    @Autowired
    private ScheduledTaskRepository scheduledTaskRepository;

    @Autowired
    private InventoryUpdater inventoryUpdater;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ScriptRunAuditEntryRepository scriptRunAuditEntryRepository;

    /**
     * Checks to see if sync_inventory needs to be ran on a schedule. This schedule
     * has yet to be determined.
     */
    @Scheduled(fixedRate = 5000)
    public void checkForJobs() throws Exception {

    }

}
