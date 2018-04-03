package com.indiraactive.fulfillmentplatform.domain;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import org.omg.CORBA.INTERNAL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class Job {
    private Integer id;
    private Integer supplierId;
    private String createdByUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDateTime;
    private boolean runOnce;
    private String cronExpression;
    private boolean active;

    public Job() { }

    public Job(Integer supplierId, String createdByUser, LocalDateTime startDateTime, boolean runOnce, String cronExpression, boolean active) {
        this.supplierId = supplierId;
        this.createdByUser = createdByUser;
        this.startDateTime = startDateTime;
        this.runOnce = runOnce;
        this.cronExpression = cronExpression;
        this.active = active;
    }

    public Job(Integer id, Integer supplierId, String createdByUser, LocalDateTime startDateTime, boolean runOnce, String cronExpression, boolean active) {
        this.id = id;
        this.supplierId = supplierId;
        this.createdByUser = createdByUser;
        this.startDateTime = startDateTime;
        this.runOnce = runOnce;
        this.cronExpression = cronExpression;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public boolean isRunOnce() {
        return runOnce;
    }

    public void setRunOnce(boolean runOnce) {
        this.runOnce = runOnce;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
