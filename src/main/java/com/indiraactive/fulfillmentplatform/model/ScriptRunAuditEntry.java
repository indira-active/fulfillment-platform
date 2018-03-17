package com.indiraactive.fulfillmentplatform.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.Date;

@Entity
@Table(name = "script_run_audit_entry")
public class ScriptRunAuditEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "script_run_audit_entry_id", length = 64, updatable = false)
    private Integer scriptRunAuditEntryId;

    @Column(name = "supplier_id", length = 64, updatable = false)
    private Integer supplierId;

    @Column(name = "success_code", nullable = false)
    private String successCode;

    @Column(name = "start_date_time", nullable = false)
    private long startDateTime;

    @Column(name = "finish_date_time", nullable = false)
    private long finishDateTime;

    @Column(name = "user_triggered", nullable = false)
    private String userTriggered;

    public Integer getScriptRunAuditEntryId() {
        return scriptRunAuditEntryId;
    }

    public void setScriptRunAuditEntryId(Integer scriptRunAuditEntryId) {
        this.scriptRunAuditEntryId = scriptRunAuditEntryId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public long getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(long startDateTime) {
        this.startDateTime = startDateTime;
    }

    public long getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(long finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public String getUserTriggered() {
        return userTriggered;
    }

    public void setUserTriggered(String userTriggered) {
        this.userTriggered = userTriggered;
    }
}
