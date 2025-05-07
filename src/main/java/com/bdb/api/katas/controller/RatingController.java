package com.bdb.api.katas.controller;

import com.bdb.api.katas.business.RatingBusiness;
import com.bdb.api.katas.dto.RatingRequestDTO;
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
@RequestMapping("api/v1/ratings")
@RequiredArgsConstructor
@Validated
public class RatingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);
    private final RatingBusiness ratingBusiness;



    @GetMapping
    public ResponseEntity<ResponseDTO> getAllRatingsWithPodium(@RequestHeader(value = "X-RqUID", required = false, defaultValue = "") String uid,
                                                               @RequestHeader(value = "X-Channel", required = false, defaultValue = "") String channel,
                                                               @RequestHeader(value = "X-Name", required = false, defaultValue = "") String name,
                                                               @RequestHeader(value = "X-Agreement", required = false, defaultValue = "") String agreement,
                                                               @RequestHeader(value = "X-IPAddr", required = false, defaultValue = "") String ip,
                                                               @RequestHeader(value = "X-api-key", required = false, defaultValue = "") String apiKey) {
        var headers = HeadersDTO.builder().uid(uid).channel(channel).name(name).agreement(agreement).ip(ip).apiKey(apiKey).build();
        LOGGER.info("Start get all Ratings with Rquid : [{}], Headers: [{}]", uid, Utilities.obfuscate(headers));

        var responseRatingWithPodium = ratingBusiness.getAllRatingsWithPodium(headers);
        return BuildResponseDTO.successResponse(BusinessCodeEnums.RATINGS_BY_SCORE, responseRatingWithPodium, uid);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> saveRating(@RequestHeader(value = "X-RqUID", required = false, defaultValue = "") String uid,
                                                       @RequestHeader(value = "X-Channel", required = false, defaultValue = "") String channel,
                                                       @RequestHeader(value = "X-Name", required = false, defaultValue = "") String name,
                                                       @RequestHeader(value = "X-Agreement", required = false, defaultValue = "") String agreement,
                                                       @RequestHeader(value = "X-IPAddr", required = false, defaultValue = "") String ip,
                                                       @RequestHeader(value = "X-api-key", required = false, defaultValue = "") String apiKey,
                                                       @Valid @RequestBody RatingRequestDTO ratingDTO){

        var headers = HeadersDTO.builder().uid(uid).channel(channel).name(name).agreement(agreement).ip(ip).apiKey(apiKey).build();
        LOGGER.info("Start save Rating with Rquid : [{}], Headers: [{}]", uid, Utilities.obfuscate(headers));
        var responseSaveRating = ratingBusiness.saveRating(ratingDTO, headers);
        return BuildResponseDTO.successResponse(BusinessCodeEnums.RATINGS, responseSaveRating, uid);

    }
}
