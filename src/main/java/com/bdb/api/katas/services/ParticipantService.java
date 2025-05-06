package com.bdb.api.katas.services;

import com.bdb.api.katas.dto.ParticipantDTO;

import java.util.List;

public interface ParticipantService {

    public List<ParticipantDTO> getAllParticipants();

    public ParticipantDTO saveParticipant(ParticipantDTO participantDTO);
}
