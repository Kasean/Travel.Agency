package com.kasean.test.dao;


import com.kasean.test.model.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourDao extends CrudRepository<Tour, Long> {

}
