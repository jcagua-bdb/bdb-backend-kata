package com.bdb.api.katas.dto.app;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MetaDTO {
    private String ServerStatusCode;
    private String StatusDesc;
    private String RqUID;

    private String Date;

    private final String Version = "1.0.0";

    public MetaDTO(String ServerStatusCode,String StatusDesc, String RqUID ) {
        this.ServerStatusCode = ServerStatusCode;
        this.StatusDesc = StatusDesc;
        this.RqUID = RqUID;
        this.Date = LocalDateTime.now().toString();
    }
}
