package com.tudasam.breakabletoyii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BreakableToyIiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BreakableToyIiApplication.class, args);
	}

	@Bean
	public RestClient restClient(OAuth2AuthorizedClientManager authorizedClientManager) {
		OAuth2ClientHttpRequestInterceptor interceptor = new OAuth2ClientHttpRequestInterceptor(authorizedClientManager);
		return RestClient.builder()
				.requestInterceptor(interceptor)
				.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/me").allowedOrigins("http://127.0.0.1:3000/").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
				registry.addMapping("/me/top/artists").allowedOrigins("http://127.0.0.1:3000/");
				registry.addMapping("/").allowedOrigins("http://127.0.0.1:3000/");
				registry.addMapping("/callback").allowedOrigins("http://127.0.0.1:3000/");
				registry.addMapping("https://accounts.spotify.com/authorize").allowedOrigins("http://127.0.0.1:3000/");
			}
		};
	}




}
