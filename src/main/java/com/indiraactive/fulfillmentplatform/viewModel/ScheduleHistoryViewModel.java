package com.indiraactive.fulfillmentplatform.viewModel;

import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import com.indiraactive.fulfillmentplatform.factory.ScheduleHistoryModelFactory;
import com.indiraactive.fulfillmentplatform.model.ScheduledHistoryModel;
import com.indiraactive.fulfillmentplatform.model.ScheduledTask;
import com.indiraactive.fulfillmentplatform.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;

public class ScheduleHistoryViewModel {
    private SupplierRepository supplierRepository;
    private List<ScheduledTask> scheduleTasks;

    public ScheduleHistoryViewModel(Iterable<ScheduledTask> scheduleTasks, SupplierRepository supplierRepository) {
        this.scheduleTasks = (List<ScheduledTask>) scheduleTasks;
        this.supplierRepository = supplierRepository;
    }

    public List<ScheduledHistoryModel> getScheduleHistoryModels() {
        List<ScheduledHistoryModel> scheduledHistoryModels = new LinkedList<>();
        for (ScheduledTask scheduleTask : scheduleTasks) {
            ScheduledHistoryModel scheduledHistoryModel;

            Integer supplierId = scheduleTask.getSupplierIdToRun();
            String supplierNameToRun = supplierRepository.findOne(supplierId).getSupplierName();
            String daysToRun = scheduleTask.getTimesToRun();
            Integer supplierIdToRun = scheduleTask.getSupplierIdToRun();

            scheduledHistoryModel = ScheduleHistoryModelFactory.createScheduleHistoryModel(supplierNameToRun,
                    daysToRun, supplierIdToRun);

            scheduledHistoryModels.add(scheduledHistoryModel);
        }

        return scheduledHistoryModels;
    }
}
