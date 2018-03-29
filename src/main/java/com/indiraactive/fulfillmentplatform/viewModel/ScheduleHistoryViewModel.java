package com.indiraactive.fulfillmentplatform.viewModel;

import com.indiraactive.fulfillmentplatform.dal.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.model.ScheduledTaskHistoryModel;
import com.indiraactive.fulfillmentplatform.dal.scheduledTask.ScheduledTaskJpa;

import java.util.LinkedList;
import java.util.List;

public class ScheduleHistoryViewModel {
    private SupplierRepository supplierRepository;
    private List<ScheduledTaskJpa> scheduleTasks;

    public ScheduleHistoryViewModel(Iterable<ScheduledTaskJpa> scheduleTasks, SupplierRepository supplierRepository) {
        this.scheduleTasks = (List<ScheduledTaskJpa>) scheduleTasks;
        this.supplierRepository = supplierRepository;
    }

    public List<ScheduledTaskHistoryModel> getScheduleHistoryModels() {
        List<ScheduledTaskHistoryModel> scheduledTaskHistoryModels = new LinkedList<>();
        for (ScheduledTaskJpa scheduleTask : scheduleTasks) {
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
