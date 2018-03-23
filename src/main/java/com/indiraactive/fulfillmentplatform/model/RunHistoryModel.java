package com.indiraactive.fulfillmentplatform.model;

import java.util.Date;

/**
 * A collection of properties that represent an individual run of the
 * sync_inventory script.
 */
public class RunHistoryModel {
    /**
     * The name of the supplier that was updated in the run
     */
    private String supplierName;

    /**
     * The audit id associated with this particular run
     */
    private Integer scriptRunAuditEntryId;

    /**
     * Whether or not the script ran to a success
     */
    private String successCode;

    /**
     * Time the script began running
     */
    private Date startDateTime;

    /**
     * Time the script ended
     */
    private Date endDateTime;

    /**
     * The name of the user that triggered the run
     */
    private String userTriggered;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getScriptRunAuditEntryId() {
        return scriptRunAuditEntryId;
    }

    public void setScriptRunAuditEntryId(Integer scriptRunAuditEntryId) {
        this.scriptRunAuditEntryId = scriptRunAuditEntryId;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getUserTriggered() {
        return userTriggered;
    }

    public void setUserTriggered(String userTriggered) {
        this.userTriggered = userTriggered;
    }
}
