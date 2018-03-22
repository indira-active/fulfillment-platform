package com.indiraactive.fulfillmentplatform.model;


import javax.persistence.*;
import java.sql.Time;
import java.util.List;

/**
 * This model represents an individual task that needs to be ran. It contains
 * when the task is to be ran and what to run.
 *
 * Todo: ***Improve data model in future enhancements, this
 * Todo: is a stable prototype/proof of concept***
 */
@Entity
@Table(name = "scheduled_task")
public class ScheduledTask {
    /**
     * Unique number representing a specific scheduled task
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scheduled_task_id", length = 64, updatable = false)
    private Integer scheduledTaskId;

    /**
     * Unique number that represents the supplier to run at the scheduled
     * time interval
     * TODO: Add foreign constraint
     */
    @Column(name="supplier_id_to_run", length = 64, updatable = false)
    private Integer supplierIdToRun;

    /**
     * Which days of the week to run on. The string is to be parsed
     * to determine which days of the week the script is to run on. For
     * example, a valid string would be mt, representing monday and tuesday.
     * Order does not matter. The valid chars that represent days of the week are
     * s (sunday), m (monday), t (tuesday), w (wednesday), u (thursday),
     * f (friday), a (saturday)
     */
    @Column(name = "times_to_run")
    private String timesToRun;

    /**
     * The user that created this scheduled task
     */
    @Column(name="created_by_user")
    private String createdByUser;

    @Column(name="hour")
    private int hour;

    @Column(name="minute")
    private int minute;

    @Column(name="month")
    private int month;

    @Column(name="day")
    private int day;

    public Integer getScheduledTaskId() {
        return scheduledTaskId;
    }

    public void setScheduledTaskId(Integer scheduledTaskId) {
        this.scheduledTaskId = scheduledTaskId;
    }

    public Integer getSupplierIdToRun() {
        return supplierIdToRun;
    }

    public void setSupplierIdToRun(Integer supplierIdToRun) {
        this.supplierIdToRun = supplierIdToRun;
    }

    public String getTimesToRun() {
        return timesToRun;
    }

    public void setTimesToRun(String timesToRun) {
        this.timesToRun = timesToRun;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduledTask that = (ScheduledTask) o;

        if (hour != that.hour) return false;
        if (minute != that.minute) return false;
        if (month != that.month) return false;
        if (day != that.day) return false;
        if (scheduledTaskId != null ? !scheduledTaskId.equals(that.scheduledTaskId) : that.scheduledTaskId != null)
            return false;
        if (supplierIdToRun != null ? !supplierIdToRun.equals(that.supplierIdToRun) : that.supplierIdToRun != null)
            return false;
        if (timesToRun != null ? !timesToRun.equals(that.timesToRun) : that.timesToRun != null) return false;
        return createdByUser != null ? createdByUser.equals(that.createdByUser) : that.createdByUser == null;
    }

    @Override
    public int hashCode() {
        int result = scheduledTaskId != null ? scheduledTaskId.hashCode() : 0;
        result = 31 * result + (supplierIdToRun != null ? supplierIdToRun.hashCode() : 0);
        result = 31 * result + (timesToRun != null ? timesToRun.hashCode() : 0);
        result = 31 * result + (createdByUser != null ? createdByUser.hashCode() : 0);
        result = 31 * result + hour;
        result = 31 * result + minute;
        result = 31 * result + month;
        result = 31 * result + day;
        return result;
    }

    @Override
    public String toString() {
        return "ScheduledTask{" +
                "scheduledTaskId=" + scheduledTaskId +
                ", supplierIdToRun=" + supplierIdToRun +
                ", timesToRun='" + timesToRun + '\'' +
                ", createdByUser='" + createdByUser + '\'' +
                ", hour=" + hour +
                ", minute=" + minute +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
