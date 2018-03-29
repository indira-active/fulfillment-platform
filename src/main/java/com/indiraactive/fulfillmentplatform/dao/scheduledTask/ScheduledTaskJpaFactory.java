package com.indiraactive.fulfillmentplatform.dao.scheduledTask;

import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.domain.ScheduledTask;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Creates ScheduleTask objects. Abstracts complexity that
 * may arise when mapping properties to the object.
 */
@Component
public class ScheduledTaskJpaFactory {

    @Autowired
    private SupplierRepository supplierRepository;

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
    public ScheduledTaskJpa createScheduleTaskJpa(String createdByUser, Date runOnDate, boolean isRecurringJob, ScheduledTaskRunDaysJpa runOnDays, Supplier supplier, List<ScheduledTaskRunTimeJpa> taskRunTimes) {
        ScheduledTaskJpa scheduledTaskJpa = new ScheduledTaskJpa();
        scheduledTaskJpa.setCreatedByUser(createdByUser);
        scheduledTaskJpa.setRunOnDate(runOnDate);
        scheduledTaskJpa.setReoccurringJob(isRecurringJob);
        scheduledTaskJpa.setRunOnDays(runOnDays);
        scheduledTaskJpa.setSupplier(supplier);
        scheduledTaskJpa.setTaskRunTimes(taskRunTimes);

        return scheduledTaskJpa;
    }

    public ScheduledTaskJpa createScheduleTaskJpa(ScheduledTask scheduledTask) {
        Date runOnDate = getDateFromDateTime(scheduledTask.getRunOnDate());
        String createdByUserId = "unknown"; //TODO: Map this eventually
        boolean isRecurringJob = false; // TODO: Add to view eventually
        ScheduledTaskRunDaysJpa scheduledTaskRunDaysJpa = createScheduledTaskRunDaysJpa(scheduledTask.getRunOnDays());
        Supplier supplier = supplierRepository.findOne(scheduledTask.getSupplierId());
        List<ScheduledTaskRunTimeJpa> taskRunTimes = new LinkedList<>();

        ScheduledTaskJpa scheduledTaskJpa = createScheduleTaskJpa(createdByUserId, runOnDate, isRecurringJob, scheduledTaskRunDaysJpa, supplier, taskRunTimes);

        return scheduledTaskJpa;

    }

    private ScheduledTaskRunDaysJpa createScheduledTaskRunDaysJpa(String runOnDays) {
        ScheduledTaskRunDaysJpa scheduledTaskRunDaysJpa = new ScheduledTaskRunDaysJpa();
        if (runOnDays.indexOf('s') >= 0) {
            scheduledTaskRunDaysJpa.setSunday(true);
        } else {
            scheduledTaskRunDaysJpa.setSunday(false);
        }
        if (runOnDays.indexOf('m') >= 0) {
            scheduledTaskRunDaysJpa.setMonday(true);
        } else {
            scheduledTaskRunDaysJpa.setMonday(false);
        }
        if (runOnDays.indexOf('t') >= 0) {
            scheduledTaskRunDaysJpa.setTuesday(true);
        } else {
            scheduledTaskRunDaysJpa.setTuesday(false);
        }
        if (runOnDays.indexOf('w') >= 0) {
            scheduledTaskRunDaysJpa.setWednesday(true);
        } else {
            scheduledTaskRunDaysJpa.setWednesday(false);
        }
        if (runOnDays.indexOf('h') >= 0) {
            scheduledTaskRunDaysJpa.setThursday(true);
        } else {
            scheduledTaskRunDaysJpa.setThursday(false);
        }
        if (runOnDays.indexOf('f') >= 0) {
            scheduledTaskRunDaysJpa.setFriday(true);
        } else {
            scheduledTaskRunDaysJpa.setFriday(false);
        }
        if (runOnDays.indexOf('a') >= 0) {
            scheduledTaskRunDaysJpa.setSaturday(true);
        } else {
            scheduledTaskRunDaysJpa.setSaturday(false);
        }

        return scheduledTaskRunDaysJpa;
    }

    public Date getDateFromDateTime(LocalDateTime localDateTime) {
        return Calendar.getInstance(TimeZone.getTimeZone("US/Eastern")).getTime();
    }
}
