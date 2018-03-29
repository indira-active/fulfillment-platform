package com.indiraactive.fulfillmentplatform.dao.job;

import com.indiraactive.fulfillmentplatform.domain.Job;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class JobJpaFactory {
    public JobJpa createJobJpa(Job job) {
        return new JobJpa(job.getId(), job.getSupplier(), job.getCreatedByUser(), job.getStartDateTime(),
                job.isRunOnce(), job.getCronExpression());
    }

    public JobJpa createJobJpaNoId(Job job) {
        return new JobJpa(job.getSupplier(), job.getCreatedByUser(), job.getStartDateTime(),
                job.isRunOnce(), job.getCronExpression());
    }
}
