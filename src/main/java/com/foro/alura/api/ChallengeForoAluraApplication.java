package com.foro.alura.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ChallengeForoAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeForoAluraApplication.class, args);
	}

/*	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("Config cors");
				registry.addMapping("/**").allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}*/

}


