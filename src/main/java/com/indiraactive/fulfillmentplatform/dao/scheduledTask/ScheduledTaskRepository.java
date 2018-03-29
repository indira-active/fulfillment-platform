package com.indiraactive.fulfillmentplatform.dao.scheduledTask;

import org.springframework.data.repository.CrudRepository;

/**
 * Abstraction layer to the database. Handles creating, reading, and updating suppliers
 * from a datasource.
 */
public interface ScheduledTaskRepository extends CrudRepository<ScheduledTaskJpa, Integer> {
}