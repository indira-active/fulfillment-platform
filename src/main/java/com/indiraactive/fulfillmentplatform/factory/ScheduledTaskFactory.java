package com.indiraactive.fulfillmentplatform.factory;

import com.indiraactive.fulfillmentplatform.model.ScheduledTask;
import com.indiraactive.fulfillmentplatform.model.ScheduledTaskRunDaysJpa;
import com.indiraactive.fulfillmentplatform.model.ScheduledTaskRunTimeJpa;
import com.indiraactive.fulfillmentplatform.model.Supplier;

import java.util.Date;
import java.util.List;

/**
 * Creates ScheduleTask objects. Abstracts complexity that
 * may arise when mapping properties to the object.
 */
public class ScheduledTaskFactory {
    /**
     *
     * @param createdByUser User who created the scheduled task
     * @param runOnDate The date the job will begin
     * @param isRecurringJob Does this job repeat after the first instance?
     * @param runOnDays Days of the week that the job will run on
     * @param supplier Supplier to run
     * @param taskRunTimes List of times that the job will execute on
     * @return A ScheduledTask object that represents a unique job that will be performed on a scheduled defined within the object
     */
    public static ScheduledTask createScheduleTask(String createdByUser, Date runOnDate, boolean isRecurringJob, ScheduledTaskRunDaysJpa runOnDays, Supplier supplier, List<ScheduledTaskRunTimeJpa> taskRunTimes) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.setCreatedByUser(createdByUser);
        scheduledTask.setRunOnDate(runOnDate);
        scheduledTask.setReoccurringJob(isRecurringJob);
        scheduledTask.setRunOnDays(runOnDays);
        scheduledTask.setSupplier(supplier);
        scheduledTask.setTaskRunTimes(taskRunTimes);

        return scheduledTask;
    }
}
