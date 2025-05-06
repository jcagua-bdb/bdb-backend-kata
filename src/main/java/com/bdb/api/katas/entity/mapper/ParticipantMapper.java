package com.bdb.api.katas.entity.mapper;

import com.bdb.api.katas.dto.ParticipantDTO;
import com.bdb.api.katas.entity.ParticipantEntity;

import java.util.ArrayList;
import java.util.List;

public class ParticipantMapper {

    public static List<ParticipantDTO> toParticipantListDTO(List<ParticipantEntity> participants){
        List<ParticipantDTO> participantDTOS = new ArrayList<>();
        for (ParticipantEntity participant : participants) {
            ParticipantDTO participantDTO = new ParticipantDTO();
            participantDTO.setId(participant.getId());
            participantDTO.setName(participant.getName());
            participantDTO.setEmail(participant.getEmail());
            participantDTO.setCreatedOn(participant.getCreatedOn());
            participantDTOS.add(participantDTO);
        }

        return participantDTOS;
    }

    public static ParticipantEntity toParticipantEntity(ParticipantDTO participantDTO){
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setId(participantDTO.getId());
        participantEntity.setName(participantDTO.getName());
        participantEntity.setEmail(participantDTO.getEmail());
        participantEntity.setCreatedOn(participantDTO.getCreatedOn());

        return participantEntity;
    }

    public static ParticipantDTO toParticipantDTO(ParticipantEntity participantEntity){
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setId(participantEntity.getId());
        participantDTO.setName(participantEntity.getName());
        participantDTO.setEmail(participantEntity.getEmail());
        participantDTO.setCreatedOn(participantEntity.getCreatedOn());

        return participantDTO;
    }
}
