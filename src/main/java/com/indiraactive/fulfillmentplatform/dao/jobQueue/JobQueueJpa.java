package com.indiraactive.fulfillmentplatform.dao.jobQueue;

import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.domain.Job;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job_queue")
public class JobQueueJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 64, updatable = false)
    private Integer id;

    @ManyToOne(optional=false)
    @JoinColumn(name="job_id", nullable=false, updatable = false)
    private JobJpa jobJpa;

    @Column(name="execute_date_time", nullable = false)
    private Date executeDateTime;

    @Column(name="executed", nullable = false)
    private boolean executed;

    public JobQueueJpa() {
    }

    public JobQueueJpa(Integer id, JobJpa jobJpa, Date executeDateTime, boolean executed) {
        this.id = id;
        this.jobJpa = jobJpa;
        this.executeDateTime = executeDateTime;
        this.executed = executed;
    }

    public JobQueueJpa(JobJpa jobJpa, Date executeDateTime, boolean executed) {
        this.jobJpa = jobJpa;
        this.executeDateTime = executeDateTime;
        this.executed = executed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JobJpa getJobJpa() {
        return jobJpa;
    }

    public void setJobJpa(JobJpa jobJpa) {
        this.jobJpa = jobJpa;
    }

    public Date getExecuteDateTime() {
        return executeDateTime;
    }

    public void setExecuteDateTime(Date executeDateTime) {
        this.executeDateTime = executeDateTime;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobQueueJpa that = (JobQueueJpa) o;

        if (executed != that.executed) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (jobJpa != null ? !jobJpa.equals(that.jobJpa) : that.jobJpa != null) return false;
        return executeDateTime != null ? executeDateTime.equals(that.executeDateTime) : that.executeDateTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jobJpa != null ? jobJpa.hashCode() : 0);
        result = 31 * result + (executeDateTime != null ? executeDateTime.hashCode() : 0);
        result = 31 * result + (executed ? 1 : 0);
        return result;
    }
}
