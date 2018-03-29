package com.indiraactive.fulfillmentplatform.service;

import com.indiraactive.fulfillmentplatform.dal.scheduledTask.ScheduledTaskRepository;
import com.indiraactive.fulfillmentplatform.dal.scriptRunAuditEntry.ScriptRunAuditEntryRepository;
import com.indiraactive.fulfillmentplatform.dal.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.model.ScheduledTaskJpa;
import com.indiraactive.fulfillmentplatform.model.ScheduledTaskRunDaysJpa;
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
        List<ScheduledTaskJpa> scheduledTaskJpas = (List<ScheduledTaskJpa>) scheduledTaskRepository.findAll();

        System.out.println("Size of tasks: " + scheduledTaskJpas.size());

        for (ScheduledTaskJpa scheduledTaskJpa : scheduledTaskJpas) {
            if (matchesTime(scheduledTaskJpa.getRunOnDate())) {
                inventoryUpdater.updateInventory(scheduledTaskJpa.getSupplier().getSupplierId());
                checkForRerun(scheduledTaskJpa.getScheduledTaskId());
                System.out.println("Job executed");
            }
        }
    }

    private void checkForRerun(Integer scheduledTaskId) {
        ScheduledTaskJpa scheduledTaskJpa = scheduledTaskRepository.findOne(scheduledTaskId);
        Calendar nowCalendar = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
        int dayOfWeek = nowCalendar.get(Calendar.DAY_OF_WEEK);

        scheduledTaskRepository.save(updateRunOnDate(scheduledTaskJpa, dayOfWeek));
    }

    public ScheduledTaskJpa updateRunOnDate(ScheduledTaskJpa scheduledTaskJpa, int dayOfWeek) {
        ScheduledTaskRunDaysJpa runOnDays = scheduledTaskJpa.getRunOnDays();

        // Map to Calendar integer representation of week day
        Map<Integer, Boolean> taskActivatedOnDayMap = new HashMap<>();
        taskActivatedOnDayMap.put(1, runOnDays.isMonday());
        taskActivatedOnDayMap.put(2, runOnDays.isTuesday());
        taskActivatedOnDayMap.put(3, runOnDays.isWednesday());
        taskActivatedOnDayMap.put(4, runOnDays.isThursday());
        taskActivatedOnDayMap.put(5, runOnDays.isFriday());
        taskActivatedOnDayMap.put(6, runOnDays.isSaturday());
        taskActivatedOnDayMap.put(7, runOnDays.isSunday());

        for (int i = 1; i <= 7; i++) {
            int nextDay = dayOfWeek +i;
            if (nextDay > 7) {
                nextDay = nextDay - 7;
            }
            boolean containsKey = taskActivatedOnDayMap.containsKey(nextDay);
            if (containsKey) {
                Calendar newDate = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
                newDate.setTime(scheduledTaskJpa.getRunOnDate());
                newDate.add(Calendar.DATE, i);
                scheduledTaskJpa.setRunOnDate(newDate.getTime());
                return scheduledTaskJpa;
            }
        }

        // TODO: Means that the job is not reoccurring
        scheduledTaskJpa.setHasRan(true);

        return scheduledTaskJpa;

    }

    public boolean matchesTime(Date date) {
        Calendar startCalendar = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
        startCalendar.add(Calendar.MINUTE, -11);
        Date startDate = startCalendar.getTime();
        Calendar endCalendar = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
        endCalendar.add(Calendar.MINUTE, 1);
        Date endDate = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern")).getTime();

        return date.after(startDate) && date.before(endDate);
    }
}
