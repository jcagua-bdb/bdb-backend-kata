package com.bdb.api.katas.business;

import com.bdb.api.katas.dto.app.HeadersDTO;
import com.bdb.api.katas.dto.JurieDTO;

import java.util.List;

public interface JuryBusiness {

    public List<JurieDTO> getAllJuries(HeadersDTO headers);
    public JurieDTO saveJury(JurieDTO jurieDTO, HeadersDTO headersDTO);
}
