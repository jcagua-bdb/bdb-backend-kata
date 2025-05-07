package com.bdb.api.katas.dto.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfluenceRatingDTO {

    @NotBlank(message = "El campo nombre del participante es Obligatorio")
    private String name;
    @NotNull(message = "El campo puntaje del participante es Obligatorio")
    private double average;
    @NotNull(message = "El campo estado del participante es Obligatorio")
    private boolean approved;
    @NotNull(message = "El campo positition es obligatorio")
    private int position;
}
