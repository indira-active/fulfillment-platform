package com.indiraactive.fulfillmentplatform.manager.scheduledTask;

import com.indiraactive.fulfillmentplatform.dal.ScheduledTaskRepository;
import com.indiraactive.fulfillmentplatform.dal.SupplierRepository;
import com.indiraactive.fulfillmentplatform.factory.ScheduledTaskJpaFactory;
import com.indiraactive.fulfillmentplatform.model.ScheduledTask;
import com.indiraactive.fulfillmentplatform.model.ScheduledTaskJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskManager {

    @Autowired
    private ScheduledTaskRepository scheduledTaskRepository;

    @Autowired
    private ScheduledTaskJpaFactory scheduledTaskJpaFactory;

    public void addNewScheduledTask(ScheduledTask scheduledTask) {
        ScheduledTaskJpa scheduledTaskJpa = scheduledTaskJpaFactory.createScheduleTaskJpa(scheduledTask);
        scheduledTaskRepository.save(scheduledTaskJpa);
    }

}
