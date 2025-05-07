package com.bdb.api.katas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequestDTO {

    private Long id;

    @NotNull(message = "El ID del jurado es obligatorio")
    @Min(value = 1, message = "El ID del jurado debe ser mayor a 0")
    private Integer id_jury;

    @NotNull(message = "El ID del participante es obligatorio")
    @Min(value = 1, message = "El ID del participante debe ser mayor a 0")
    private Integer id_participant;

    @NotNull(message = "El puntaje del perfil del participante  es obligatorio")
    @Range(min = 0, max = 100, message = "El puntaje del perfil del participante debe estar entre 0 y 100")
    private Integer profile;

    @NotNull(message = "El puntaje de comunicación del participante es obligatorio")
    @Range(min = 0, max = 100, message = "El puntaje de comunicacion del participante debe estar entre 0 y 100")
    private Integer communication;

    @NotNull(message = "El puntaje de Técnica del participante es obligatorio")
    @Range(min = 0, max = 100, message = "El puntaje de técnica del participante debe estar entre 0 y 100")
    private Integer technique;

    @Range(min = 0, max = 100, message = "El puntaje extra del participante debe estar entre 0 y 100")
    private Integer extraPoints;

    private Boolean state;

    private ZonedDateTime createdOn;

    private ZonedDateTime lastUpdatedOn;
}
