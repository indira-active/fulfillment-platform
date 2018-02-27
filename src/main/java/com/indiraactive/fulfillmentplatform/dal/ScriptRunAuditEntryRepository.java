package com.indiraactive.fulfillmentplatform.dal;

import com.indiraactive.fulfillmentplatform.model.ScriptRunAuditEntry;
import com.indiraactive.fulfillmentplatform.model.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface ScriptRunAuditEntryRepository extends CrudRepository<ScriptRunAuditEntry, Integer> {
}
