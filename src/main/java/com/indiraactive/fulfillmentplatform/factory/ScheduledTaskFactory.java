package com.indiraactive.fulfillmentplatform.factory;

import com.indiraactive.fulfillmentplatform.model.ScheduledTask;

/**
 * Creates ScheduleTask objects. Abstracts complexity that
 * may arise when mapping properties to the object.
 */
public class ScheduledTaskFactory {

    /**
     * Creates a ScheduleTask object by mapping the parameters to a new object.
     * @param createdByUser User who created the scheduled task
     * @param supplierIdToRun The id that represents which supplier to run
     * @param timesToRun Represents what days of the week to run on, see model for more
     *                   documentation
     * @return Newly created ScheduleTask, will need to be persisted
     */
    public static ScheduledTask createScheduleTask(String createdByUser, Integer supplierIdToRun, String timesToRun) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.setCreatedByUser(createdByUser);
        scheduledTask.setSupplierIdToRun(supplierIdToRun);
        scheduledTask.setTimesToRun(timesToRun);

        return scheduledTask;
    }
}
