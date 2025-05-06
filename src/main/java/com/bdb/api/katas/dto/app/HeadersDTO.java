package com.bdb.api.katas.dto.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeadersDTO {
    private String uid;
    private String channel;
    private String name;
    private String agreement;
    private String ip;
    private String apiKey;
    private String nit;
}
