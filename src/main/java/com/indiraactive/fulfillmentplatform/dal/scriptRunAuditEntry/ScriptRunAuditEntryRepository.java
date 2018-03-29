package com.indiraactive.fulfillmentplatform.dal.scriptRunAuditEntry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptRunAuditEntryRepository extends JpaRepository<ScriptRunAuditEntry, Integer> {
    List<ScriptRunAuditEntry> findAllByOrderByStartDateTimeAsc();
    List<ScriptRunAuditEntry> findAllByOrderByStartDateTimeDesc();
    List<ScriptRunAuditEntry> findAllByOrderByFinishDateTimeAsc();
    List<ScriptRunAuditEntry> findAllByOrderByFinishDateTimeDesc();
    List<ScriptRunAuditEntry> findAllByScriptRunAuditEntryIdOrderByStartDateTimeAsc(Integer supplierId);
}
