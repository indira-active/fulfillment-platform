package com.indiraactive.fulfillmentplatform.domain.jobQueue;

import java.time.LocalDateTime;

public class JobQueue {
    private Integer id;
    private Integer jobId;
    private LocalDateTime executeDateTime;
    private boolean executed;

    public JobQueue() {
    }

    public JobQueue(Integer jobId, LocalDateTime executeDateTime, boolean executed) {
        this.jobId = jobId;
        this.executeDateTime = executeDateTime;
        this.executed = executed;
    }

    public JobQueue(Integer id, Integer jobId, LocalDateTime executeDateTime, boolean executed) {
        this.id = id;
        this.jobId = jobId;
        this.executeDateTime = executeDateTime;
        this.executed = executed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public LocalDateTime getExecuteDateTime() {
        return executeDateTime;
    }

    public void setExecuteDateTime(LocalDateTime executeDateTime) {
        this.executeDateTime = executeDateTime;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }
}
