package com.bdb.api.katas.dto.app;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseStatusErrorDTO {

    private int statusCode;
    private String severity;
    private String serverStatusCode;
    private String statusDesc;
    private Object data;


    public ResponseStatusErrorDTO(int statusCode, String severity, String serverStatusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.severity = severity;
        this.serverStatusCode = serverStatusCode;
        this.statusDesc = statusDesc;
    }
    public ResponseStatusErrorDTO(int statusCode, String severity, String serverStatusCode, String statusDesc, Object data) {
        this.statusCode = statusCode;
        this.severity = severity;
        this.serverStatusCode = serverStatusCode;
        this.statusDesc = statusDesc;
        this.data = data;
    }
}
