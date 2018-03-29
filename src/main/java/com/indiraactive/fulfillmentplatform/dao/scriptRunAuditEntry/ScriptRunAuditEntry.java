package com.indiraactive.fulfillmentplatform.dao.scriptRunAuditEntry;

import javax.persistence.*;

/**
 * Captures a specific run of the sync_inventory script so run history can be inspected.
 */
@Entity
@Table(name = "script_run_audit_entry")
public class ScriptRunAuditEntry {
    /**
     * Id that represents a unique script audit entry
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "script_run_audit_entry_id", length = 64, updatable = false)
    private Integer scriptRunAuditEntryId;

    /**
     * The supplier ID that was used to populate the script arguments
     */
    @Column(name = "supplier_id", length = 64, updatable = false)
    private Integer supplierId;

    /**
     * Whether or not the script run was successful
     */
    @Column(name = "success_code", nullable = false)
    private String successCode;

    /**
     * The time the script started running at in epoch
     */
    @Column(name = "start_date_time", nullable = false)
    private long startDateTime;

    /**
     * The time the script finished running at in epoch
     */
    @Column(name = "finish_date_time", nullable = false)
    private long finishDateTime;

    /**
     * User who triggered the run. Eventually may need to figure out a way to capture automated runs
     * that are on a timer.
     */
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
