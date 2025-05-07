package com.bdb.api.katas.services.Impl;

import com.bdb.api.katas.dto.RatingRequestDTO;
import com.bdb.api.katas.dto.RatingResponseDTO;
import com.bdb.api.katas.dto.app.BuildResponseDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;
import com.bdb.api.katas.entity.JuryEntity;
import com.bdb.api.katas.entity.ParticipantEntity;
import com.bdb.api.katas.entity.RatingEntity;
import com.bdb.api.katas.entity.mapper.RatingMapper;
import com.bdb.api.katas.enums.BusinessCodeEnums;
import com.bdb.api.katas.repository.JuryRepository;
import com.bdb.api.katas.repository.ParticipantRepository;
import com.bdb.api.katas.repository.RatingRepository;
import com.bdb.api.katas.services.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final JuryRepository juryRepository;
    private final ParticipantRepository participantRepository;

    @Override
    public RatingRequestDTO saveRating(RatingRequestDTO ratingRequestDTO, HeadersDTO headers) {
        Optional <JuryEntity> juryOptional= juryRepository.findById( (Long.valueOf(ratingRequestDTO.getId_jury())));
        Optional <ParticipantEntity> participantOptional= participantRepository.findById( (Long.valueOf(ratingRequestDTO.getId_participant())));

        if(juryOptional.isEmpty() || participantOptional.isEmpty()){
            throw BuildResponseDTO.throwException(BusinessCodeEnums.JURY_OR_PARTICIPANT_NOT_EXISTS, headers.getUid());
        }
        return RatingMapper.toRatingRequestDTO(ratingRepository.save(RatingMapper.toRatingEntity(ratingRequestDTO,participantOptional.get(),juryOptional.get())));
    }

    @Override
    public List<RatingEntity> getAllRatingsActive(HeadersDTO header) {
        List<RatingEntity> allRatings = null;
        allRatings = ratingRepository.findAllRatingsActive();
        if (allRatings.isEmpty()){
            throw BuildResponseDTO.throwException(BusinessCodeEnums.RATINGS_NULL, header.getUid());
            }
        return allRatings ;
    }

    @Override
    public void updateRatingState() {
        ratingRepository.updateAllRatings();
    }
}
