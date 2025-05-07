package com.bdb.api.katas.repository;


import com.bdb.api.katas.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    @Query("SELECT r FROM RatingEntity r WHERE r.state = true")
    List<RatingEntity> findAllRatingsActive();

}
