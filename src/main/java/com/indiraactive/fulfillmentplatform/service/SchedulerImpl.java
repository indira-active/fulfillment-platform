package com.indiraactive.fulfillmentplatform.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Contains any methods that are schedule based
 */
@Component
public class SchedulerImpl {
    /**
     * Checks to see if sync_inventory needs to be ran on a schedule. This schedule
     * has yet to be determined.
     */
    @Scheduled(fixedRate = 5000)
    public void checkForJobs() {
        System.out.println("Perform logic for running jobs here");
    }
}
