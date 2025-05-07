package com.bdb.api.katas.entity.mapper;

import com.bdb.api.katas.dto.RatingRequestDTO;
import com.bdb.api.katas.entity.JuryEntity;
import com.bdb.api.katas.entity.ParticipantEntity;
import com.bdb.api.katas.entity.RatingEntity;

public class RatingMapper {

    public static RatingEntity toRatingEntity(RatingRequestDTO ratingRequestDTO, ParticipantEntity participantEntity, JuryEntity juryEntity) {
        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setJury(juryEntity);
        ratingEntity.setParticipants(participantEntity);
        ratingEntity.setProfile(ratingRequestDTO.getProfile());
        ratingEntity.setCommunication(ratingRequestDTO.getCommunication());
        ratingEntity.setTechnique(ratingRequestDTO.getTechnique());
        ratingEntity.setPointsExtra(ratingRequestDTO.getExtraPoints());
        ratingEntity.setState(ratingRequestDTO.getState() == null || ratingRequestDTO.getState());
        ratingEntity.setCreatedOn(ratingRequestDTO.getCreatedOn());
        ratingEntity.setLastUpdatedOn(ratingRequestDTO.getLastUpdatedOn());

        return ratingEntity;
    }

    public static RatingRequestDTO toRatingRequestDTO(RatingEntity ratingEntity){
        RatingRequestDTO ratingRequestDTO = new RatingRequestDTO();
        ratingRequestDTO.setId(ratingEntity.getId());
        ratingRequestDTO.setId_jury((int) ratingEntity.getJury().getId());
        ratingRequestDTO.setId_participant((int) ratingEntity.getParticipants().getId());
        ratingRequestDTO.setProfile(ratingEntity.getProfile());
        ratingRequestDTO.setCommunication(ratingEntity.getCommunication());
        ratingRequestDTO.setTechnique(ratingEntity.getTechnique());
        ratingRequestDTO.setExtraPoints(ratingEntity.getPointsExtra());
        ratingRequestDTO.setState(ratingEntity.getState());
        ratingRequestDTO.setCreatedOn(ratingEntity.getCreatedOn());
        ratingRequestDTO.setLastUpdatedOn(ratingEntity.getLastUpdatedOn());

        return ratingRequestDTO;
    }
}
