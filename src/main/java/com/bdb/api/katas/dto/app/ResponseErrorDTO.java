package com.bdb.api.katas.dto.app;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseErrorDTO {
    private ResponseStatusErrorDTO status;
    private String EndDt;
    private String rqUID;


    public ResponseErrorDTO(ResponseStatusErrorDTO status, String rqUID){
        this.status = status;
        this.EndDt = LocalDateTime.now().toString();
        this.rqUID = rqUID;
    }
}
