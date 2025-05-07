package com.bdb.api.katas.business;

import com.bdb.api.katas.dto.RatingRequestDTO;
import com.bdb.api.katas.dto.RatingResponseDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;

import java.util.List;

public interface RatingBusiness {

    public RatingRequestDTO saveRating(RatingRequestDTO ratingRequestDTO, HeadersDTO headers);

    public List<RatingResponseDTO> getAllRatingsWithPodium(HeadersDTO headers);
}
