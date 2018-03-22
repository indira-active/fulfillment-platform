package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dal.ScheduledTaskRepository;
import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import com.indiraactive.fulfillmentplatform.model.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Contains any methods that are schedule based
 */
@Component
public class SchedulerImpl {
    @Autowired
    private ScheduledTaskRepository scheduledTaskRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Checks to see if sync_inventory needs to be ran on a schedule. This schedule
     * has yet to be determined.
     */
    @Scheduled(fixedRate = 5000)
    public void checkForJobs() {

        for (ScheduledTask scheduledTask :scheduledTaskRepository.findAll()) {
            
        }


        System.out.println("Perform logic for running jobs here");
    }
}
