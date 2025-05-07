package com.bdb.api.katas.controller;

import com.bdb.api.katas.business.Impl.RatingBusinessImpl;
import com.bdb.api.katas.consumer.RegisterConfluenceConsumer;
import com.bdb.api.katas.dto.RegisterConfluenceDTO;
import com.bdb.api.katas.dto.app.BuildResponseDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;
import com.bdb.api.katas.dto.app.ResponseDTO;
import com.bdb.api.katas.enums.BusinessCodeEnums;
import com.bdb.api.katas.services.RatingService;
import com.bdb.api.katas.utils.Utilities;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/register-confluence")
@RequiredArgsConstructor
@Validated
public class RegisterConfluenceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterConfluenceController.class);
    private final RegisterConfluenceConsumer registerConfluenceConsumer;
    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<ResponseDTO> registerConfluence(@RequestHeader(value = "X-RqUID", required = false, defaultValue = "") String uid,
                                                          @RequestHeader(value = "X-Channel", required = false, defaultValue = "") String channel,
                                                          @RequestHeader(value = "X-Name", required = false, defaultValue = "") String name,
                                                          @RequestHeader(value = "X-Agreement", required = false, defaultValue = "") String agreement,
                                                          @RequestHeader(value = "X-IPAddr", required = false, defaultValue = "") String ip,
                                                          @RequestHeader(value = "X-api-key", required = false, defaultValue = "") String apiKey,
                                                          @Valid @RequestBody RegisterConfluenceDTO registerConfluenceDTO){
        var headers = HeadersDTO.builder().uid(uid).channel(channel).name(name).agreement(agreement).ip(ip).apiKey(apiKey).build();
        LOGGER.info("Start get all Ratings with Rquid : [{}], Headers: [{}]", uid, Utilities.obfuscate(headers));

        var responseRegisterConfluence = registerConfluenceConsumer.registerConfluenceConsumer(registerConfluenceDTO, headers);
        if (responseRegisterConfluence.getStatusCode().value() == 200){
            ratingService.updateRatingState();
        }
        return BuildResponseDTO.successResponse(BusinessCodeEnums.REGISTER, responseRegisterConfluence, uid);
    }
}
