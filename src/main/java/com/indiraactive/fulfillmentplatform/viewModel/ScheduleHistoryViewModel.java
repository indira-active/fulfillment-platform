package com.indiraactive.fulfillmentplatform.viewModel;

import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import com.indiraactive.fulfillmentplatform.model.ScheduledTaskHistoryModel;
import com.indiraactive.fulfillmentplatform.model.ScheduledTask;

import java.util.LinkedList;
import java.util.List;

public class ScheduleHistoryViewModel {
    private SupplierRepository supplierRepository;
    private List<ScheduledTask> scheduleTasks;

    public ScheduleHistoryViewModel(Iterable<ScheduledTask> scheduleTasks, SupplierRepository supplierRepository) {
        this.scheduleTasks = (List<ScheduledTask>) scheduleTasks;
        this.supplierRepository = supplierRepository;
    }

    public List<ScheduledTaskHistoryModel> getScheduleHistoryModels() {
        List<ScheduledTaskHistoryModel> scheduledTaskHistoryModels = new LinkedList<>();
        for (ScheduledTask scheduleTask : scheduleTasks) {
            ScheduledTaskHistoryModel scheduledTaskHistoryModel;

//            Integer supplierId = scheduleTask.getSupplierIdToRun();
//            String supplierNameToRun = supplierRepository.findOne(supplierId).getSupplierName();
//            String daysToRun = scheduleTask.getTimesToRun();
//            Integer supplierIdToRun = scheduleTask.getSupplierIdToRun();

//            scheduledTaskHistoryModel = ScheduleHistoryModelFactory.createScheduleHistoryModel(supplierNameToRun,
//                    daysToRun, supplierIdToRun);
//
//            scheduledTaskHistoryModels.add(scheduledTaskHistoryModel);
        }

        return scheduledTaskHistoryModels;
    }
}
