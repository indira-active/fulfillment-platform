package com.indiraactive.fulfillmentplatform.domain;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;

import java.util.Date;

public class Job {
    private Integer id;
    private Supplier supplier;
    private String createdByUser;
    private Date startDateTime;
    private boolean runOnce;
    private String cronExpression;

    public Job() { }

    public Job(Supplier supplier, String createdByUser, Date startDateTime, boolean runOnce, String cronExpression) {
        this.id = id;
        this.supplier = supplier;
        this.createdByUser = createdByUser;
        this.startDateTime = startDateTime;
        this.runOnce = runOnce;
        this.cronExpression = cronExpression;
    }

    public Job(Integer id, Supplier supplier, String createdByUser, Date startDateTime, boolean runOnce, String cronExpression) {
        this.id = id;
        this.supplier = supplier;
        this.createdByUser = createdByUser;
        this.startDateTime = startDateTime;
        this.runOnce = runOnce;
        this.cronExpression = cronExpression;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
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
