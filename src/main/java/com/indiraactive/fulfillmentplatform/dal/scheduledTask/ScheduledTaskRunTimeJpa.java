package com.indiraactive.fulfillmentplatform.dal.scheduledTask;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ScheduledTaskRunTimeJpa {
    @Column(name="time_epoch_to_run_task")
    private Long timeEpochToRunTask;

    public Long getTimeEpochToRunTask() {
        return timeEpochToRunTask;
    }

    public void setTimeEpochToRunTask(Long timeEpochToRunTask) {
        this.timeEpochToRunTask = timeEpochToRunTask;
    }
}
