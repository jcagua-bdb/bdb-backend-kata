package com.bdb.api.katas.exceptions;

import com.bdb.api.katas.dto.app.ResponseErrorDTO;

public class InvalidInputException extends RuntimeException {

    private final transient ResponseErrorDTO responseError;

    public InvalidInputException(ResponseErrorDTO responseError) {
        super(responseError.getStatus().getStatusDesc());
        this.responseError = responseError;
    }

    public ResponseErrorDTO getResponse() {
        return responseError;
    }
}
