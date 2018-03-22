package com.indiraactive.fulfillmentplatform.model;

public class ScheduledHistoryModel {
    private String supplierName;
    private String runFrequency;
    private Integer scheduleTaskId;
    private int hour;
    private int minute;
    private int month;
    private int day;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getRunFrequency() {
        return runFrequency;
    }

    public void setRunFrequency(String runFrequency) {
        this.runFrequency = runFrequency;
    }

    public Integer getScheduleTaskId() {
        return scheduleTaskId;
    }

    public void setScheduleTaskId(Integer scheduleTaskId) {
        this.scheduleTaskId = scheduleTaskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduledHistoryModel that = (ScheduledHistoryModel) o;

        if (supplierName != null ? !supplierName.equals(that.supplierName) : that.supplierName != null) return false;
        return runFrequency != null ? runFrequency.equals(that.runFrequency) : that.runFrequency == null;
    }

    @Override
    public int hashCode() {
        int result = supplierName != null ? supplierName.hashCode() : 0;
        result = 31 * result + (runFrequency != null ? runFrequency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScheduledHistoryModel{" +
                "supplierName='" + supplierName + '\'' +
                ", runFrequency='" + runFrequency + '\'' +
                '}';
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
