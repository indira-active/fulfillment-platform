package com.indiraactive.fulfillmentplatform.domain;

import com.indiraactive.fulfillmentplatform.dao.job.JobJpa;
import com.indiraactive.fulfillmentplatform.dao.supplier.Supplier;
import com.indiraactive.fulfillmentplatform.utility.CalendarSync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

@Component
public class JobFactory {
    @Autowired
    private CalendarSync calendarSync;

    public Job createJob(JobJpa jobJpa) {
        return new Job(jobJpa.getId(), jobJpa.getSupplier().getSupplierId(), jobJpa.getCreatedByUser(), calendarSync.getLocalDateTimeFromDate(jobJpa.getStartDateTime()), jobJpa.isRunOnce(), jobJpa.getCronExpression());
    }

    public Job createJob(Supplier supplier, String createdByUser, Date startDateTime, boolean runOnce, String cronExpression) {
        return new Job(supplier.getSupplierId(), createdByUser, calendarSync.getLocalDateTimeFromDate(startDateTime), runOnce, cronExpression);
    }
}
