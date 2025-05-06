package com.bdb.api.katas.repository;

import com.bdb.api.katas.entity.JuryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JuryRepository extends JpaRepository<JuryEntity, Long> {

    @Query("SELECT j FROM JuryEntity j")
    List<JuryEntity> findAllJuries();
}
