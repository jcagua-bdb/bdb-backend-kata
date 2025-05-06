package com.bdb.api.katas.business;

import com.bdb.api.katas.dto.JurieDTO;
import com.bdb.api.katas.dto.ParticipantDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;

import java.util.List;

public interface ParticipantBusiness {
    public List<ParticipantDTO> getAllParticipants(HeadersDTO headers);
    public ParticipantDTO saveParticipant(ParticipantDTO participantDTO, HeadersDTO headersDTO);
}
