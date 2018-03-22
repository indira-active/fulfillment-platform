package com.indiraactive.fulfillmentplatform.factory;

import com.indiraactive.fulfillmentplatform.model.ScheduledHistoryModel;

public class ScheduleHistoryModelFactory {
    public static ScheduledHistoryModel createScheduleHistoryModel(String supplierName, String runFrequency, Integer scheduleTaskId) {
        ScheduledHistoryModel scheduledHistoryModel = new ScheduledHistoryModel();

        scheduledHistoryModel.setRunFrequency(runFrequency);
        scheduledHistoryModel.setSupplierName(supplierName);
        scheduledHistoryModel.setScheduleTaskId(scheduleTaskId);

        return scheduledHistoryModel;
    }
}
