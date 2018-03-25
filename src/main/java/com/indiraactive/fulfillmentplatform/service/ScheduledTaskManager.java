package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dal.ScheduledTaskRepository;
import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import com.indiraactive.fulfillmentplatform.model.ScheduledTaskJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handles persisting data between the database and retrieving persisted data
 * for the view.
 */
@Component
public class ScheduledTaskManager {
    /**
     * Used for persisting schedules tasks/crud operations
     */
    @Autowired
    private ScheduledTaskRepository scheduledTaskRepository;

    /**
     * Used for retrieving supplier information
     */
    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Saves a scheduled task to the database
     * @param scheduledTaskJpa The task to be saved
     * @return The task that was saved
     */
    public ScheduledTaskJpa addNewScheduledTask(ScheduledTaskJpa scheduledTaskJpa) {
        if (scheduledTaskJpa.getCreatedByUser() == null ) {
            scheduledTaskJpa.setCreatedByUser("not_set");
            System.out.println("Need to implement user management for logging purposes, failed to log user in addNewScheduledTask");
        }
        return scheduledTaskRepository.save(scheduledTaskJpa);
    }

    /**
     * Delete a scheduled task by id
     * @param scheduledTaskId The id of the scheduled task to be deleted
     */
    public void deleteScheduleTask(Integer scheduledTaskId) {
        scheduledTaskRepository.delete(scheduledTaskId);
    }

    /**
     * Retrieve all scheduled tasks that are active
     * @return Scheduled tasks that are active
     */
    public Iterable<ScheduledTaskJpa> getScheduledTasks() {
        return scheduledTaskRepository.findAll();
    }
}
