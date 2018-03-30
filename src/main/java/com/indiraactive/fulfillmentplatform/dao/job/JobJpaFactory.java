package com.indiraactive.fulfillmentplatform.dao.job;

import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.dao.supplier.SupplierRepository;
import com.indiraactive.fulfillmentplatform.domain.Job;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class JobJpaFactory {
    @Autowired
    private CalendarSync calendarSync;

    @Autowired
    private SupplierRepository supplierRepository;

    public JobJpa createJobJpa(Job job) {
        Supplier supplier = supplierRepository.findOne(job.getSupplierId());

        return new JobJpa(job.getId(), supplier, job.getCreatedByUser(), calendarSync.getDateFromDateTime(job.getStartDateTime()),
                job.isRunOnce(), job.getCronExpression());
    }

    public JobJpa createJobJpaNoId(Job job) {
        Supplier supplier = supplierRepository.findOne(job.getSupplierId());

        return new JobJpa(supplier, job.getCreatedByUser(), calendarSync.getDateFromDateTime(job.getStartDateTime()),
                job.isRunOnce(), job.getCronExpression());
    }
}
