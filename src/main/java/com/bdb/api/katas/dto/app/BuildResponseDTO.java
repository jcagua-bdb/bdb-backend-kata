package com.bdb.api.katas.dto.app;

import com.bdb.api.katas.enums.BusinessCodeEnums;
import com.bdb.api.katas.exceptions.InvalidInputException;
import org.springframework.http.ResponseEntity;

public class BuildResponseDTO {

    public static InvalidInputException throwException(BusinessCodeEnums codeEnum, String uid){
        var responseError = getResponseError(codeEnum, uid);
        return new InvalidInputException(responseError);
    }
    public static ResponseErrorDTO getResponseError(BusinessCodeEnums codeEnum, String uid){
        var responseError = new ResponseStatusErrorDTO(
                codeEnum.getHttpStatus(),
                "Error",
                codeEnum.getCode(),
                codeEnum.getDescription());
        return new ResponseErrorDTO(responseError, uid);
    }
    public static ResponseErrorDTO getResponseErrorWithData(BusinessCodeEnums codeEnum, Object data, String uid){
        var responseError = new ResponseStatusErrorDTO(
                codeEnum.getHttpStatus(),
                "Error",
                codeEnum.getCode(),
                codeEnum.getDescription(),
                data
        );
        return new ResponseErrorDTO(responseError, uid);
    }
    public static ResponseEntity<ResponseDTO> successResponse(BusinessCodeEnums codeEnum, Object object, String uid){
        var meta = new MetaDTO(
                codeEnum.getCode(),
                codeEnum.getDescription(),
                uid
        );
        var response = new ResponseDTO(object, meta);
        return ResponseEntity
                .status(codeEnum.getHttpStatus())
                .body(response);
    }
}
