package com.bdb.api.katas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {

    private long id;

    @NotBlank(message = "El nombre del participante no puede venir vacío")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del participante solo puede contener letras")
    @Size(min = 1, max = 30 , message = "El nombre del participante debe tener entre 5 y 30 caracteres")
    private String name;

    @NotBlank(message = "El correo corporativo del participante no puede venir vacío")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@bancodebogota\\.com\\.co$", message = "El correo corporativo debe estar compuesto por @bancodebogot.com.co")
    private String email;

    private ZonedDateTime createdOn;
}
