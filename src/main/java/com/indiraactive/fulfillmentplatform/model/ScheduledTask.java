package com.indiraactive.fulfillmentplatform.model;

import com.indiraactive.fulfillmentplatform.model.db.Supplier;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ScheduledTask {
    private Integer supplierId;
    private String createdByUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime runOnDate;

    private List<ScheduledTaskRunTimeJpa> taskRunTimes;
    private String runOnDays;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public LocalDateTime getRunOnDate() {
        return runOnDate;
    }

    public void setRunOnDate(LocalDateTime runOnDate) {
        this.runOnDate = runOnDate;
    }

    public List<ScheduledTaskRunTimeJpa> getTaskRunTimes() {
        return taskRunTimes;
    }

    public void setTaskRunTimes(List<ScheduledTaskRunTimeJpa> taskRunTimes) {
        this.taskRunTimes = taskRunTimes;
    }

    public String getRunOnDays() {
        return runOnDays;
    }

    public void setRunOnDays(String runOnDays) {
        this.runOnDays = runOnDays;
    }
}