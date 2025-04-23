package com.tudasam.breakabletoyii.controller;


import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Secured";
    }

    @GetMapping("/callback")
    public String callback(OAuth2AuthenticationToken authentication) {
        OAuth2User user = authentication.getPrincipal();
        return "Welcome " + user.getName();
    }
}
