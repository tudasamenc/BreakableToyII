package com.tudasam.breakabletoyii.SpotifyData;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyService {
    private final RestTemplate restTemplate;

    public SpotifyService() {
        restTemplate = new RestTemplate();
    }


    public String requestAccessToken(String code) {
        String url = "https://accounts.spotify.com/api/token";
        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Body as form data
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code",code);
        body.add("redirect_uri", "http://127.0.0.1:8080/callback");
        // Combine headers and body
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        // Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println("PostReq");
        // Output the response
        System.out.println(response.getBody());
        return response.getBody();
    }
}

