package com.bdb.api.katas.enums;

public enum BusinessCodeEnums {

    //Juries
    JURIES(200, "B-001KT", "Lista de jurados retornada exitosamente"),
    JURY(201, "B-002KT", "Jurado creado exitosamente"),
    JURIES_ERROR(500, "T-001KT", "Error genérico del servidor"),

    // Participants
    PARTICIPANTS(200, "B-003KT", "Lista de participantes retornada exitosamente"),
    PARTICIPANT(201, "B-004KT", "Participante creado exitosamente"),

    //Calificaciones
    RATINGS(201, "B-005KT", "Califación creada exitosamente"),
    JURY_OR_PARTICIPANT_NOT_EXISTS(404, "B-006KT", "El jurado o el participante no existe con los IDs dados "),
    RATINGS_NULL(404, "B-006KT", "No hay registros de calificaciones para el jurado o el participante dado"),
    RATINGS_BY_SCORE(200, "B-007KT", "Lista de calificaciones retornada exitosamente"),

    //CONFLUENCE
    REGISTER(201, "B-008KT", "Registro exitoso en Confluence"),
    ERROR_REGISTER(500, "T-003KT", "Error al registrar en Confluence"),
    //APP
    BAD_REQUEST_HEADERS(403, "T-004KT", "No autorizado, headers incompletos o con información erronea."),
    DATABASE_EXCEPTION(500, "T-002KT", "Server Error"),
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
