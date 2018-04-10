package com.indiraactive.fulfillmentplatform.dao.job;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "job")
public class JobJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 64, updatable = false)
    private Integer id;

    @ManyToOne(optional=false)
    @JoinColumn(name="supplier_id", nullable=false, updatable = false)
    private Supplier supplier;

    @Column(name="created_by_user", nullable = false, updatable = false)
    private String createdByUser;

    @Column(name="start_date_time", nullable = false)
    private Date startDateTime;

    @Column(name="run_once", nullable = false)
    private boolean runOnce;

    @Column(name = "cron_expression")
    private String cronExpression;

    @Column(name = "active")
    private boolean active;

    public JobJpa() { }

    public JobJpa(Integer id, Supplier supplier, String createdByUser, Date startDateTime, boolean runOnce, String cronExpression, boolean active) {
        this.id = id;
        this.supplier = supplier;
        this.createdByUser = createdByUser;
        this.startDateTime = startDateTime;
        this.runOnce = runOnce;
        this.cronExpression = cronExpression;
        this.active = active;
    }

    public JobJpa(Supplier supplier, String createdByUser, Date startDateTime, boolean runOnce, String cronExpression, boolean active) {
        this.supplier = supplier;
        this.createdByUser = createdByUser;
        this.startDateTime = startDateTime;
        this.runOnce = runOnce;
        this.cronExpression = cronExpression;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobJpa jobJpa = (JobJpa) o;

        if (runOnce != jobJpa.runOnce) return false;
        if (id != null ? !id.equals(jobJpa.id) : jobJpa.id != null) return false;
        if (supplier != null ? !supplier.equals(jobJpa.supplier) : jobJpa.supplier != null) return false;
        if (createdByUser != null ? !createdByUser.equals(jobJpa.createdByUser) : jobJpa.createdByUser != null)
            return false;
        if (startDateTime != null ? !startDateTime.equals(jobJpa.startDateTime) : jobJpa.startDateTime != null)
            return false;
        return cronExpression != null ? cronExpression.equals(jobJpa.cronExpression) : jobJpa.cronExpression == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (supplier != null ? supplier.hashCode() : 0);
        result = 31 * result + (createdByUser != null ? createdByUser.hashCode() : 0);
        result = 31 * result + (startDateTime != null ? startDateTime.hashCode() : 0);
        result = 31 * result + (runOnce ? 1 : 0);
        result = 31 * result + (cronExpression != null ? cronExpression.hashCode() : 0);
        return result;
    }
}
