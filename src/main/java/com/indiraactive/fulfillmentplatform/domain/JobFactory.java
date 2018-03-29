package com.indiraactive.fulfillmentplatform.domain;

import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

@Component
public class JobFactory {
    public Job createJob(JobJpa jobJpa) {
        return new Job(jobJpa.getId(), jobJpa.getSupplier(), jobJpa.getCreatedByUser(), jobJpa.getStartDateTime(), jobJpa.isRunOnce(), jobJpa.getCronExpression());
    }

    public Job createJob(Supplier supplier, String createdByUser, Date startDateTime, boolean runOnce, String cronExpression) {
        return new Job(supplier, createdByUser, startDateTime, runOnce, cronExpression);
    }
}
