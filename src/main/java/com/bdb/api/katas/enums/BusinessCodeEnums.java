package com.bdb.api.katas.enums;

public enum BusinessCodeEnums {

    //Juries
    JURIES(200, "B-001KT", "Lista de jurados retornada exitosamente"),
    JURY(201, "B-002KT", "Jurado creado exitosamente"),
    JURIES_ERROR(500, "T-001KT", "Error genérico del servidor"),

    //APP
    BAD_REQUEST_HEADERS(403, "T-004KT", "No autorizado, headers incompletos o con información erronea."),
    DATABASE_EXCEPTION(500, "T-002KT", "Problemas de persistencia en la base de datos"),
    BAD_REQUEST(400, "T-003KT", "Los datos enviados en la solicitud no cumplen con la definición del API");
    private Integer httpStatus;
    private String code;
    private String description;

    BusinessCodeEnums(Integer httpStatus, String code, String description) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.description = description;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
