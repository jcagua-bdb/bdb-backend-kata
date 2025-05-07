package com.bdb.api.katas.services;


import com.bdb.api.katas.dto.RatingRequestDTO;
import com.bdb.api.katas.dto.RatingResponseDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;
import com.bdb.api.katas.entity.RatingEntity;

import java.util.List;

public interface RatingService {

    public RatingRequestDTO saveRating(RatingRequestDTO ratingRequestDTO, HeadersDTO headers);

    public List<RatingEntity> getAllRatingsActive(HeadersDTO headers);
}
