package com.bdb.api.katas.business.Impl;

import com.bdb.api.katas.business.RatingBusiness;
import com.bdb.api.katas.dto.RatingRequestDTO;
import com.bdb.api.katas.dto.RatingResponseDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;
import com.bdb.api.katas.entity.RatingEntity;
import com.bdb.api.katas.services.RatingService;
import com.bdb.api.katas.utils.Validations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
@Slf4j
@RequiredArgsConstructor
public class RatingBusinessImpl implements RatingBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingBusinessImpl.class);
    private final RatingService ratingService;

    @Override
    public RatingRequestDTO saveRating(RatingRequestDTO ratingRequestDTO, HeadersDTO headers) {
        Validations.handleInvalidHeaders(headers);
        LOGGER.info("Start save Rating with Rquid : [{}]", headers.getUid());
        return ratingService.saveRating(ratingRequestDTO, headers);
    }

    @Override
    public List<RatingResponseDTO> getAllRatingsWithPodium(HeadersDTO headers) {
        List<RatingEntity> ratingList ;

        Validations.handleInvalidHeaders(headers);
        LOGGER.info("Start get all Ratings with Rquid : [{}]", headers.getUid());
        ratingList = ratingService.getAllRatingsActive(headers);

        return processResponsePodium(ratingList);
    }


    private static  List<RatingResponseDTO> processResponsePodium(List<RatingEntity> ratingList){
        List<RatingResponseDTO> processedRatingsByParticipants = new ArrayList<>();
        Map<Long, List<RatingEntity>> ratingsByParticipantId = new HashMap<>();

       for (RatingEntity ratingEntity : ratingList) {
           if (ratingEntity.getParticipants() != null){
               Long participantId = ratingEntity.getParticipants().getId();
               //Agrupa los participantes por ID
               ratingsByParticipantId.computeIfAbsent(participantId, k -> new ArrayList<>()).add(ratingEntity);
           }

       }


       for (Map.Entry<Long, List<RatingEntity>> entry : ratingsByParticipantId.entrySet()) {
            Long participantId = entry.getKey();
            List<RatingEntity> participantRatings = entry.getValue();
            String participantName = participantRatings.get(0).getParticipants().getName();
            if (participantName == null || participantName.trim().isEmpty()){
                participantName = "N/A";
            }

            double totalScoreSum = 0;
            int numberOfJurors = participantRatings.size();

            for (RatingEntity rating : participantRatings) {
                totalScoreSum += calculateScoreByParticipants(rating);
            }

            //PROMEDIO = totalScore / numero_jurados
            double averageScore = totalScoreSum / numberOfJurors;
            // se divide por 100 para generar puntaje en terminos enteros y dejar dos decimales
            averageScore = Math.round(averageScore * 100.0) / 100.0;

            // APRUEBA O NO >= 75%
            boolean isAproveed = averageScore >= 75;

            RatingResponseDTO responseDTO = new RatingResponseDTO(participantId, participantName, averageScore, isAproveed);
            processedRatingsByParticipants.add(responseDTO);
       }

        // Ordenando por promedio de calificación
        processedRatingsByParticipants.sort(Comparator.comparing(RatingResponseDTO::getAverage, Comparator.reverseOrder())
                .thenComparing(RatingResponseDTO::getName));

        // Empieza a asignar posiciones
       for (int i = 0; i < processedRatingsByParticipants.size(); i++) {
           processedRatingsByParticipants.get(i).setPosition(i+1);
       }

       return processedRatingsByParticipants;
    }

    // Metodo para calcular el Score por participante
    private static double calculateScoreByParticipants(RatingEntity rating){

        double profileScore = rating.getProfile() != null ? rating.getProfile() : 0;
        double communicationScore = rating.getCommunication() != null ? rating.getCommunication() : 0;
        double techniqueScore = rating.getTechnique() != null ? rating.getTechnique() : 0;
        double extraPoints =  rating.getPointsExtra() != null ? rating.getPointsExtra() : 0;

        // calculo porcentajes
        double score = (profileScore * 0.10) + (communicationScore * 0.35) + (techniqueScore * 0.55);

        return score + extraPoints;

    }

}
