package com.bdb.api.katas.consumer;

import com.bdb.api.katas.dto.RegisterConfluenceDTO;
import com.bdb.api.katas.dto.app.BuildResponseDTO;
import com.bdb.api.katas.dto.app.HeadersDTO;
import com.bdb.api.katas.enums.BusinessCodeEnums;
import com.bdb.api.katas.utils.Utilities;
import com.bdb.api.katas.utils.Validations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegisterConfluenceConsumerImpl implements RegisterConfluenceConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterConfluenceConsumerImpl.class);

    @Value("${confluence.api.url}")
    private String confluenceApiUrl;

    @Value("${confluence.api.token}")
    private String confluenceApiToken;

    @Value("${confluence.api.userName}")
    private String confluenceUserName;

    @Value("${mock.api.url}")
    private String mockApiUrl;

    @Value("${mock.api.token}")
    private String mockApiToken;

    @Override
    public ResponseEntity registerConfluenceConsumer(RegisterConfluenceDTO registerConfluenceDTO, HeadersDTO headers) {
        Validations.handleInvalidHeaders(headers);
        String htmlRegisterContent = Utilities.buildHtmlTableRegisterConfluence(registerConfluenceDTO.getParticipantsRatings());
        String bodyRequest = bodyRequestConfluence(registerConfluenceDTO, htmlRegisterContent);
        var processMockoon = consumerApiHttp(mockApiUrl, mockApiToken, confluenceUserName , bodyRequest, headers.getUid());
        LOGGER.info("Response process mockoon STATUS : [{}], RESPONSE: [{}]", processMockoon.getStatusCode(), processMockoon.getBody());
        return consumerApiHttp(confluenceApiUrl, confluenceApiToken,confluenceUserName, bodyRequest, headers.getUid());
    }





    private static ResponseEntity consumerApiHttp(String confluenceApiUrl, String confluenceApiToken, String confluenceUserName, String bodyRequest, String uid) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(confluenceApiUrl);

        //Authorization header
        post.setHeader("Authorization", "Basic " + confluenceApiToken   );
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(bodyRequest, "UTF-8"));


        HttpResponse response = null;
        String responseBody = null;
        try {
            response = client.execute(post);
            responseBody = EntityUtils.toString(response.getEntity());
            LOGGER.info("Confluence API Response STATUS : [{}], RESPONSE: [{}]", response.getStatusLine().getStatusCode(), responseBody);
            if (response.getStatusLine().getStatusCode() != 200){
                LOGGER.error("Error al registro en Confluence : [{}]", responseBody);
                throw BuildResponseDTO.throwException(BusinessCodeEnums.ERROR_REGISTER, uid);
            }
        } catch (Exception e) {
            throw BuildResponseDTO.throwException(BusinessCodeEnums.ERROR_REGISTER, uid);
        }

        try {
            return ResponseEntity.status(response.getStatusLine().getStatusCode()).body(responseBody);
        } catch (Exception e) {
            throw BuildResponseDTO.throwException(BusinessCodeEnums.ERROR_REGISTER, uid);
        }
    }



    private static String bodyRequestConfluence(RegisterConfluenceDTO registerConfluenceDTO, String htmlContent){
        return String.format("""
                {
                  "type": "page",
                  "title": "%s",
                  "space": { "key": "%s" },
                  "body": {
                    "storage": {
                      "value": "%s",
                      "representation": "storage"
                    }
                  }
                }
                """, registerConfluenceDTO.getPageTitle(), registerConfluenceDTO.getSpaceKey(), htmlContent.replace("\"", "\\\""));
    }

}
