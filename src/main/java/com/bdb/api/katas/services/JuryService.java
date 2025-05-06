package com.bdb.api.katas.services;

import com.bdb.api.katas.dto.JurieDTO;

import java.util.List;

public interface JuryService {

    public List<JurieDTO> getAllJuries();

    public JurieDTO saveJury(JurieDTO jurieDTO);
}
