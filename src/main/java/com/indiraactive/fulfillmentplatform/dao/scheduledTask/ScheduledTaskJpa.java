package com.indiraactive.fulfillmentplatform.dao.scheduledTask;


import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;

import javax.persistence.*;
import java.util.Date;
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
public class ScheduledTaskJpa {
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
     */
    @ManyToOne(optional=false)
    @JoinColumn(name="supplier_id", nullable=false, updatable=false)
    private Supplier supplier;

    /**
     * The user that created this scheduled task
     * TODO: In future implementation create a reference to a table that has
     * TODO: user information stored. Use a foreign key instead.
     */
    @Column(name="created_by_user")
    private String createdByUser;

    /**
     * The date of the first run of the job.
     */
    @Column(name="start_on_date")
    private Date runOnDate;

    /**
     * The set of times in a day that a job will run
     */
    @ElementCollection
    @CollectionTable(
            name="scheduled_task_run_times",
            joinColumns=@JoinColumn(name="scheduled_task_id")
    )
    private List<ScheduledTaskRunTimeJpa> taskRunTimes;

    /**
     * Whether or not the job will run after the runOnDate
     */
    @Column(name="reoccurring_job")
    private boolean reoccurringJob;

    /**
     * Days of the week that the job will run on
     */
    @Embedded
    private ScheduledTaskRunDaysJpa runOnDays;

    @Column(name="has_ran")
    private Boolean hasRan;

    public Boolean hasRan() {
        return hasRan;
    }

    public void setHasRan(Boolean hasRan) {
        this.hasRan = hasRan;
    }

    public Integer getScheduledTaskId() {
        return scheduledTaskId;
    }

    public void setScheduledTaskId(Integer scheduledTaskId) {
        this.scheduledTaskId = scheduledTaskId;
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

    public Date getRunOnDate() {
        return runOnDate;
    }

    public void setRunOnDate(Date runOnDate) {
        this.runOnDate = runOnDate;
    }

    public List<ScheduledTaskRunTimeJpa> getTaskRunTimes() {
        return taskRunTimes;
    }

    public void setTaskRunTimes(List<ScheduledTaskRunTimeJpa> taskRunTimes) {
        this.taskRunTimes = taskRunTimes;
    }

    public boolean isReoccurringJob() {
        return reoccurringJob;
    }

    public void setReoccurringJob(boolean reoccurringJob) {
        this.reoccurringJob = reoccurringJob;
    }

    public ScheduledTaskRunDaysJpa getRunOnDays() {
        return runOnDays;
    }

    public void setRunOnDays(ScheduledTaskRunDaysJpa runOnDays) {
        this.runOnDays = runOnDays;
    }
}
