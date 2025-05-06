package com.bdb.api.katas.utils;

import com.bdb.api.katas.dto.app.BuildResponseDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;
import com.bdb.api.katas.enums.BusinessCodeEnums;

public class Validations {
    public static void handleInvalidHeaders(HeadersDTO headers){
        if (headers.getUid().isEmpty() ||
                headers.getChannel().isEmpty() ||
                headers.getName().isEmpty() ||
                headers.getAgreement().isEmpty() ||
                headers.getIp().isEmpty() ||
                headers.getApiKey().isEmpty()){
            throw BuildResponseDTO.throwException(BusinessCodeEnums.BAD_REQUEST_HEADERS, headers.getUid());
        }
    }
}
