package com.bdb.api.katas.repository;

import com.bdb.api.katas.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

    @Query("SELECT p FROM ParticipantEntity p")
    List<ParticipantEntity> findAllParticipant();
}
