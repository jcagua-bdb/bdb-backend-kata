package com.bdb.api.katas.entity.mapper;

import com.bdb.api.katas.dto.JurieDTO;
import com.bdb.api.katas.entity.JuryEntity;

import java.util.ArrayList;
import java.util.List;

public class JuryMapper {

    public static List<JurieDTO> toJuryListDTO(List<JuryEntity> juries){
        List<JurieDTO> jurieDTOS = new ArrayList<>();
        for (JuryEntity jurie : juries) {
            JurieDTO jurieDTO = new JurieDTO();
            jurieDTO.setId(jurie.getId());
            jurieDTO.setName(jurie.getName());
            jurieDTO.setEmail(jurie.getEmail());
            jurieDTO.setCreatedOn(jurie.getCreatedOn());
            jurieDTOS.add(jurieDTO);
        }

        return jurieDTOS;
    }

    public static JurieDTO toJuryDTO(JuryEntity jurie){
        JurieDTO jurieDTO = new JurieDTO();
        jurieDTO.setId(jurie.getId());
        jurieDTO.setName(jurie.getName());
        jurieDTO.setEmail(jurie.getEmail());
        jurieDTO.setCreatedOn(jurie.getCreatedOn());

        return jurieDTO;
    }

    public static JuryEntity toJuryEntity(JurieDTO jurieDTO){
        JuryEntity jurieEntity = new JuryEntity();
        jurieEntity.setId(jurieDTO.getId());
        jurieEntity.setName(jurieDTO.getName());
        jurieEntity.setEmail(jurieDTO.getEmail());
        jurieEntity.setCreatedOn(jurieDTO.getCreatedOn());

        return jurieEntity;
    }
}
