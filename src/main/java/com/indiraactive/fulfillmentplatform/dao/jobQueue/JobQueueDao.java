package com.indiraactive.fulfillmentplatform.dao.jobQueue;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobQueueDao extends CrudRepository<JobQueueJpa, Integer> {
    List<JobQueueJpa> findByExecutedOrderByExecuteDateTime(boolean isExecuted);
}
