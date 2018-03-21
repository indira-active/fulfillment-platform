package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dal.ScheduledTask;
import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private ScheduledTask scheduledTask;

    /**
     * Used for retrieving supplier information
     */
    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Saves a scheduled task to the database
     * @param scheduledTask The task to be saved
     * @return The task that was saved
     */
    public ScheduledTask addNewScheduledTask(ScheduledTask scheduledTask) {
        return scheduledTask.save(scheduledTask);
    }

    /**
     * Delete a scheduled task by id
     * @param scheduledTaskId The id of the scheduled task to be deleted
     */
    public void deleteScheduleTask(Integer scheduledTaskId) {
        scheduledTask.delete(scheduledTaskId);
    }

    /**
     * Retrieve all scheduled tasks that are active
     * @return List of scheduled tasks that are active
     */
    public List<ScheduledTask> getScheduledTasks() {
        return (List<ScheduledTask>) scheduledTask.findAll();
    }
}
