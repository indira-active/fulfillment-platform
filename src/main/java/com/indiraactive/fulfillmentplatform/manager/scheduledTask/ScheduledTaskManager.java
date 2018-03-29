package com.indiraactive.fulfillmentplatform.manager.scheduledTask;

import com.indiraactive.fulfillmentplatform.dao.scheduledTask.ScheduledTaskRepository;
import com.indiraactive.fulfillmentplatform.dao.scheduledTask.ScheduledTaskJpaFactory;
import com.indiraactive.fulfillmentplatform.domain.ScheduledTask;
import com.indiraactive.fulfillmentplatform.dao.scheduledTask.ScheduledTaskJpa;
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
