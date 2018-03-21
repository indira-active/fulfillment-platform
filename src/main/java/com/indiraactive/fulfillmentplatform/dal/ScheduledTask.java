package com.indiraactive.fulfillmentplatform.dal;

import org.springframework.data.repository.CrudRepository;

/**
 * Abstraction layer to the database. Handles creating, reading, and updating scheduled tasks
 * from a datasource.
 */
public interface ScheduledTask extends CrudRepository<ScheduledTask, Integer> {
}
