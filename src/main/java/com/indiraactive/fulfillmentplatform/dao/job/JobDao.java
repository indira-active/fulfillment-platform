package com.indiraactive.fulfillmentplatform.dao.job;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface JobDao extends CrudRepository<JobJpa, Integer> {
    List<JobJpa> findByStartDateTimeBeforeAndActive(Date startDateTime, boolean isActive);
}
