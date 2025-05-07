package com.bdb.api.katas.controller;

import com.bdb.api.katas.business.ParticipantBusiness;
import com.bdb.api.katas.dto.ParticipantDTO;
import com.bdb.api.katas.dto.app.BuildResponseDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;
import com.bdb.api.katas.dto.app.ResponseDTO;
import com.bdb.api.katas.enums.BusinessCodeEnums;
import com.bdb.api.katas.utils.Utilities;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/participants")
@RequiredArgsConstructor
@Validated
public class ParticipantController {

    private final ParticipantBusiness participantBusiness;
    private static final Logger LOGGER = LoggerFactory.getLogger(ParticipantController.class);

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllJuries(@RequestHeader(value = "X-RqUID", required = false, defaultValue = "") String uid,
                                                    @RequestHeader(value = "X-Channel", required = false, defaultValue = "") String channel,
                                                    @RequestHeader(value = "X-Name", required = false, defaultValue = "") String name,
                                                    @RequestHeader(value = "X-Agreement", required = false, defaultValue = "") String agreement,
                                                    @RequestHeader(value = "X-IPAddr", required = false, defaultValue = "") String ip,
                                                    @RequestHeader(value = "X-api-key", required = false, defaultValue = "") String apiKey){
        var headers = HeadersDTO.builder().uid(uid).channel(channel).name(name).agreement(agreement).ip(ip).apiKey(apiKey).build();
        LOGGER.info("Start get all participants with Rquid : [{}], Headers: [{}]", uid, Utilities.obfuscate(headers));

        var responseGetAllParticipants = participantBusiness.getAllParticipants(headers);
        return BuildResponseDTO.successResponse(BusinessCodeEnums.PARTICIPANTS, responseGetAllParticipants, uid);

    }

    @PostMapping
    public ResponseEntity<ResponseDTO> saveParticipant(@RequestHeader(value = "X-RqUID", required = false, defaultValue = "") String uid,
                                                       @RequestHeader(value = "X-Channel", required = false, defaultValue = "") String channel,
                                                       @RequestHeader(value = "X-Name", required = false, defaultValue = "") String name,
                                                       @RequestHeader(value = "X-Agreement", required = false, defaultValue = "") String agreement,
                                                       @RequestHeader(value = "X-IPAddr", required = false, defaultValue = "") String ip,
                                                       @RequestHeader(value = "X-api-key", required = false, defaultValue = "") String apiKey,
                                                       @Valid @RequestBody ParticipantDTO participantDTO){
        var headers = HeadersDTO.builder().uid(uid).channel(channel).name(name).agreement(agreement).ip(ip).apiKey(apiKey).build();

        LOGGER.info("Start save participant with Rquid : [{}], Headers: [{}]", uid, Utilities.obfuscate(headers));
        var responseSaveParticipant = participantBusiness.saveParticipant(participantDTO, headers);
        return BuildResponseDTO.successResponse(BusinessCodeEnums.PARTICIPANT, responseSaveParticipant, uid);
    }
}
