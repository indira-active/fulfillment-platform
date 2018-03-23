package com.indiraactive.fulfillmentplatform.dal;

import com.indiraactive.fulfillmentplatform.model.db.ScriptRunAuditEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScriptRunAuditEntryRepository extends JpaRepository<ScriptRunAuditEntry, Integer> {
    List<ScriptRunAuditEntry> findAllByOrderByStartDateTimeAsc();
    List<ScriptRunAuditEntry> findAllByOrderByStartDateTimeDesc();
    List<ScriptRunAuditEntry> findAllByOrderByFinishDateTimeAsc();
    List<ScriptRunAuditEntry> findAllByOrderByFinishDateTimeDesc();
}
