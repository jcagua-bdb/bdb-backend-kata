package com.bdb.api.katas.consumer;

import com.bdb.api.katas.dto.RegisterConfluenceDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterConfluenceConsumer {

    public ResponseEntity registerConfluenceConsumer(RegisterConfluenceDTO registerConfluenceDTO , HeadersDTO headers);
}
