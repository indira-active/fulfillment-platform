package com.indiraactive.fulfillmentplatform.model;

import java.util.Date;

public class RunHistoryModel {
    private String supplierName;
    private Integer scriptRunAuditEntryId;
    private String successCode;
    private Date startDateTime;
    private Date endDateTime;
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
