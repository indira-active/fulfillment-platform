package com.indiraactive.fulfillmentplatform.domain;

import java.util.Date;
import java.util.List;

public class ScheduledTaskHistoryModel {
    private Integer scheduledTaskId;
    private String jobName;
    private String supplierName;
    private List<Date> runOnDate;
    private String runOnDays;
    private boolean isRecurringJob;

    public Integer getScheduledTaskId() {
        return scheduledTaskId;
    }

    public void setScheduledTaskId(Integer scheduledTaskId) {
        this.scheduledTaskId = scheduledTaskId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<Date> getRunOnDate() {
        return runOnDate;
    }

    public void setRunOnDate(List<Date> runOnDate) {
        this.runOnDate = runOnDate;
    }

    public String getRunOnDays() {
        return runOnDays;
    }

    public void setRunOnDays(String runOnDays) {
        this.runOnDays = runOnDays;
    }

    public boolean isRecurringJob() {
        return isRecurringJob;
    }

    public void setRecurringJob(boolean recurringJob) {
        isRecurringJob = recurringJob;
    }
}
