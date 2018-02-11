package com.indiraactive.stockupdaterserver.dal;

import com.indiraactive.stockupdaterserver.model.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {

}