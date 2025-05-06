package com.bdb.api.katas.business.Impl;

import com.bdb.api.katas.business.JuryBusiness;
import com.bdb.api.katas.dto.app.BuildResponseDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;
import com.bdb.api.katas.dto.JurieDTO;
import com.bdb.api.katas.enums.BusinessCodeEnums;
import com.bdb.api.katas.services.JuryService;
import com.bdb.api.katas.utils.Validations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JuryBusinessImpl implements JuryBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(JuryBusinessImpl.class);
    private  final JuryService  juryService;

    @Override
    public List<JurieDTO> getAllJuries(HeadersDTO headers) {

        Validations.handleInvalidHeaders(headers);
        LOGGER.info("Start get all juries with Rquid : [{}]", headers.getUid());
        List<JurieDTO> juryList = null;
        try {
            juryList = juryService.getAllJuries();
        } catch (Exception ex) {
            BuildResponseDTO.throwException(BusinessCodeEnums.JURIES_ERROR, headers.getUid());
        }
        return juryList;

    }

    @Override
    public JurieDTO saveJury(JurieDTO jurieDTO, HeadersDTO headersDTO) {
        Validations.handleInvalidHeaders(headersDTO);
        LOGGER.info("Start save jury with Rquid : [{}]", headersDTO.getUid());
        JurieDTO juryDTO = null;
        juryDTO = juryService.saveJury(jurieDTO);
        return juryDTO;
    }
}
