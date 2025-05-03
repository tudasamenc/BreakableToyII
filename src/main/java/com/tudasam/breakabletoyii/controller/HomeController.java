package com.tudasam.breakabletoyii.controller;


import com.tudasam.breakabletoyii.SpotifyData.SpotifyArtist;


import java.util.Arrays;
import java.util.List;

import com.tudasam.breakabletoyii.SpotifyData.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
@CrossOrigin

public class HomeController {
    @Autowired
    SpotifyService spotifyService;
    private String codeCallback;
    // POST Authentication
    @PostMapping("/auth/spotify")
    String authenticateSpotify() {
        System.out.println("authenticateSpotify");
        //spotifyService.requestAccessToken(codeCallback);

        return "Authentication Successful";
    }

    // GET Most played artists
    @GetMapping("/me/top/artists")
    List<SpotifyArtist> getTopArtists(OAuth2AuthenticationToken token) {
        return getTopArtists(token);
    }

    //GET specific artist
    @GetMapping("/artists/{id}")
    String findArtist(@PathVariable String id){

        return id;
    }

    @GetMapping("/ABC")
    String testingAPI(){

        return "ABC";

    }







    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Secured";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code) {
       // OAuth2User user = authentication.getPrincipal();
        System.out.println(code);
        codeCallback = code;
        //spotifyService.requestAccessToken(codeCallback);
        return "Welcome " + code;
    }


}
