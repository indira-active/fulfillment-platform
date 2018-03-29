package com.indiraactive.fulfillmentplatform.dal.supplier;

import org.springframework.data.repository.CrudRepository;

/**
 * Abstraction layer to the database. Handles creating, reading, and updating suppliers
 * from a datasource.
 */
public interface SupplierRepository extends CrudRepository<Supplier, Integer> {

}