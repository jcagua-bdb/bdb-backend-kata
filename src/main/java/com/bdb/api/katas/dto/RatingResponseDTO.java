package com.bdb.api.katas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponseDTO {

    private Long participantId;
    private String name;
    private Double average;
    private Boolean approved;
    private Integer position;

    public RatingResponseDTO(Long participantId, String participantName, double averageScore, boolean isAproveed) {
        this.participantId = participantId;
        this.name = participantName;
        this.average = averageScore;
        this.approved = isAproveed;
        this.position = 0;
    }
}
