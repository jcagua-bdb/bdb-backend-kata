package com.bdb.api.katas.dto;

import com.bdb.api.katas.dto.app.ConfluenceRatingDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterConfluenceDTO {

    @NotBlank(message = "El campo titulo página confluence es obligatorio")
    private String pageTitle;

    @NotBlank(message = "El campo spaceKey es obligatorio")
    private String spaceKey;

    private List< @Valid ConfluenceRatingDTO> participantsRatings;
}
