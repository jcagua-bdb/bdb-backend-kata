package com.bdb.api.katas.exceptions;

import com.bdb.api.katas.dto.app.BuildResponseDTO;
import com.bdb.api.katas.dto.app.ResponseErrorDTO;
import com.bdb.api.katas.enums.BusinessCodeEnums;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<ResponseErrorDTO> handleInvalidInputException(InvalidInputException ex) {
        LOGGER.error("[Error]: {}, Rquid: [{}]", "Handler Global Exception (INVALID INPUT EXCEPTION): " + ex.getMessage(),  ex.getResponse().getRqUID());
        return ResponseEntity
                .status(ex.getResponse().getStatus().getStatusCode())
                .body(ex.getResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDTO> handleExeption(Exception ex, HttpServletRequest request) {

        HttpHeaders headers = new HttpHeaders();
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> headers.add(headerName, request.getHeader(headerName)));
        var uid = headers.get("x-rquid").get(0);
        LOGGER.error("[Error]: {}, Rquid: [{}]", "Handler Global Exception (HANDLE EXCEPTION): " + ex.getMessage(),  uid);
        if (ex instanceof ConstraintViolationException constraintViolationException) {
            return getErrorsParams(constraintViolationException, uid);
        }
        if (ex instanceof MethodArgumentNotValidException argumentNotValid) {
            return getErrorsFromDto(argumentNotValid, uid);
        }
        if (ex.getCause() != null) {
            if (ex.getCause().getStackTrace() != null){
                LOGGER.error("[Error]: {}, Rquid: [{}]", "Handler Global Exception (HANDLE EXCEPTION2): " + Arrays.stream(ex.getCause().getStackTrace()).toList(),  uid);
            }
        }

        var responseError = BuildResponseDTO.getResponseErrorWithData(BusinessCodeEnums.DATABASE_EXCEPTION, ex.getMessage(), uid);
        return ResponseEntity
                .status(BusinessCodeEnums.DATABASE_EXCEPTION.getHttpStatus())
                .body(responseError);
    }

    private ResponseEntity<ResponseErrorDTO> getErrorsParams(ConstraintViolationException constraintViolationException, String uid) {

        Map<String, List<String>> errors = new HashMap<>();
        constraintViolationException.getConstraintViolations().forEach(error -> {
            LOGGER.info("getPropertyPath " + error.getPropertyPath().toString());

            String key = error.getPropertyPath().toString().split("\\.")[1];
            boolean ispresent = errors.containsKey(key);
            if (ispresent) {
                var listErrros = errors.get(key);
                errors.put(key, Stream.concat(Stream.of(error.getMessageTemplate()), listErrros.stream()).toList());
            } else {
                errors.put(key, List.of(error.getMessageTemplate()));
            }
        });
        LOGGER.error("[Error]: {}, Rquid: [{}]", "Handler Global Exception (GET ERROR PARAMS): " + errors,  uid);
        var responseError = BuildResponseDTO.getResponseErrorWithData(BusinessCodeEnums.BAD_REQUEST, errors, uid);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseError);
    }

    private ResponseEntity<ResponseErrorDTO> getErrorsFromDto(MethodArgumentNotValidException argumentNotValid, String uid) {
        Map<String, String> errors = new HashMap<>();
        argumentNotValid.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        LOGGER.error("[Error]: {}, Rquid: [{}]", "Handler Global Exception (GET ERROR FROM DTO): " + errors,  uid);
        var responseError = BuildResponseDTO.getResponseErrorWithData(BusinessCodeEnums.BAD_REQUEST, errors, uid);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseError);
    }
}
