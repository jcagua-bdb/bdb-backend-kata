package com.bdb.api.katas.business.Impl;

import com.bdb.api.katas.business.ParticipantBusiness;
import com.bdb.api.katas.dto.ParticipantDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;
import com.bdb.api.katas.services.ParticipantService;
import com.bdb.api.katas.utils.Validations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ParticipantBusinessImpl implements ParticipantBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantBusinessImpl.class);
    private final ParticipantService participantService;


    @Override
    public List<ParticipantDTO> getAllParticipants(HeadersDTO headers) {
        Validations.handleInvalidHeaders(headers);
        LOGGER.info("Start get all juries with Rquid : [{}]", headers.getUid());
        List<ParticipantDTO> participantList = null;
        participantList = participantService.getAllParticipants();
        return participantList;
    }

    @Override
    public ParticipantDTO saveParticipant(ParticipantDTO participantDTO, HeadersDTO headersDTO) {
        Validations.handleInvalidHeaders(headersDTO);
        LOGGER.info("Start save participant with Rquid : [{}]", headersDTO.getUid());
        ParticipantDTO participantDTO1 = null;
        participantDTO1 = participantService.saveParticipant(participantDTO);
        return participantDTO1;
    }
}
