package com.indiraactive.fulfillmentplatform.dal;

import com.indiraactive.fulfillmentplatform.model.db.ScriptRunAuditEntry;
import org.springframework.data.repository.CrudRepository;

public interface ScriptRunAuditEntryRepository extends CrudRepository<ScriptRunAuditEntry, Integer> {
}
