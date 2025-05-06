package com.bdb.api.katas.controller;

import com.bdb.api.katas.business.JuryBusiness;
import com.bdb.api.katas.dto.JurieDTO;
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
@RequestMapping("api/v1/juries")
@RequiredArgsConstructor
@Validated
public class JuryController {

    private final JuryBusiness jurieBusiness;
    private static final Logger LOGGER = LoggerFactory.getLogger(JuryController.class);

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllJuries(@RequestHeader(value = "X-RqUID", required = false, defaultValue = "") String uid,
                                                    @RequestHeader(value = "X-Channel", required = false, defaultValue = "") String channel,
                                                    @RequestHeader(value = "X-Name", required = false, defaultValue = "") String name,
                                                    @RequestHeader(value = "X-Agreement", required = false, defaultValue = "") String agreement,
                                                    @RequestHeader(value = "X-IPAddr", required = false, defaultValue = "") String ip,
                                                    @RequestHeader(value = "X-api-key", required = false, defaultValue = "") String apiKey){
        var headers = HeadersDTO.builder().uid(uid).channel(channel).name(name).agreement(agreement).ip(ip).apiKey(apiKey).build();
        LOGGER.info("Start get all juries with Rquid : [{}], Headers: [{}]", uid, Utilities.obfuscate(headers));

        var responseGetAllJuries = jurieBusiness.getAllJuries(headers);
        return BuildResponseDTO.successResponse(BusinessCodeEnums.JURIES, responseGetAllJuries, uid);

    }

    @PostMapping
    public ResponseEntity<ResponseDTO> saveJury(@RequestHeader(value = "X-RqUID", required = false, defaultValue = "") String uid,
                                                @RequestHeader(value = "X-Channel", required = false, defaultValue = "") String channel,
                                                @RequestHeader(value = "X-Name", required = false, defaultValue = "") String name,
                                                @RequestHeader(value = "X-Agreement", required = false, defaultValue = "") String agreement,
                                                @RequestHeader(value = "X-IPAddr", required = false, defaultValue = "") String ip,
                                                @RequestHeader(value = "X-api-key", required = false, defaultValue = "") String apiKey,
                                                @Valid @RequestBody JurieDTO juryDTO){
        var headers = HeadersDTO.builder().uid(uid).channel(channel).name(name).agreement(agreement).ip(ip).apiKey(apiKey).build();

        LOGGER.info("Start save jury with Rquid : [{}], Headers: [{}]", uid, Utilities.obfuscate(headers));
        var responseSaveJury = jurieBusiness.saveJury(juryDTO,headers);
        return BuildResponseDTO.successResponse(BusinessCodeEnums.JURY, responseSaveJury, uid);
    }
}
