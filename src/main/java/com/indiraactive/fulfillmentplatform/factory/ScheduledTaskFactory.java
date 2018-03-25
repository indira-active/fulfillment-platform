package com.indiraactive.fulfillmentplatform.factory;

import com.indiraactive.fulfillmentplatform.model.ScheduledTaskJpa;
import com.indiraactive.fulfillmentplatform.model.ScheduledTaskRunDaysJpa;
import com.indiraactive.fulfillmentplatform.model.ScheduledTaskRunTimeJpa;
import com.indiraactive.fulfillmentplatform.model.db.Supplier;

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
     * @return A ScheduledTaskJpa object that represents a unique job that will be performed on a scheduled defined within the object
     */
    public static ScheduledTaskJpa createScheduleTask(String createdByUser, Date runOnDate, boolean isRecurringJob, ScheduledTaskRunDaysJpa runOnDays, Supplier supplier, List<ScheduledTaskRunTimeJpa> taskRunTimes) {
        ScheduledTaskJpa scheduledTaskJpa = new ScheduledTaskJpa();
        scheduledTaskJpa.setCreatedByUser(createdByUser);
        scheduledTaskJpa.setRunOnDate(runOnDate);
        scheduledTaskJpa.setReoccurringJob(isRecurringJob);
        scheduledTaskJpa.setRunOnDays(runOnDays);
        scheduledTaskJpa.setSupplier(supplier);
        scheduledTaskJpa.setTaskRunTimes(taskRunTimes);

        return scheduledTaskJpa;
    }
}
