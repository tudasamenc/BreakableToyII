package com.tudasam.breakabletoyii.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;



@RestController

public class HomeController {

    private final RestClient restClient;

    public HomeController(RestClient restClient) {
        this.restClient = restClient;
    }
    //GET Personal Info
    @GetMapping("/me")
    public String me() {
        return restClient.get()
                .uri("https://api.spotify.com/v1/me")
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .body(String.class);
    }

    //GET specific artist
    @GetMapping("/artists/{id}")
    String findArtist(@PathVariable String id){
        return restClient.get()
                .uri("https://api.spotify.com/v1/artists/"+id)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .body(String.class);
    }
    //GET specific album
    @GetMapping("/albums/{id}")
    String findAlbum(@PathVariable String id){
        return restClient.get()
                .uri("https://api.spotify.com/v1/albums/"+id)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .body(String.class);
    }

    //GET specific album tracks
    @GetMapping("/albums/{id}/tracks")
    String findAlbumTracks(@PathVariable String id){
        return restClient.get()
                .uri("https://api.spotify.com/v1/albums/"+id+"/tracks")
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .body(String.class);
    }

    // GET Most played artists
    @GetMapping("/me/top/artists")
    String getTopArtists(){
        return restClient.get()
                .uri("https://api.spotify.com/v1/me/top/artists")
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .body(String.class);
    }

    // POST Authentication
    @PostMapping("/auth/spotify")
    String authenticateSpotify() {
        System.out.println("authenticateSpotify");
        //spotifyService.requestAccessToken(codeCallback);

        return "Authentication Successful";
    }


    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code) {
        System.out.println(code);
        return "Welcome " + code;
    }

}
