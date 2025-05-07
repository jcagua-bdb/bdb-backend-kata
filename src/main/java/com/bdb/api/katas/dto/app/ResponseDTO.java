package com.bdb.api.katas.dto.app;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseDTO {
    private Object data;

    private MetaDTO meta;


    public ResponseDTO(Object data, MetaDTO meta){
        this.data = data;
        this.meta = meta;
    }
}
