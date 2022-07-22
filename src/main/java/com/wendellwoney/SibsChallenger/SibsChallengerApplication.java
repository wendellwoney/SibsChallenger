package com.wendellwoney.SibsChallenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SibsChallengerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SibsChallengerApplication.class, args);
	}
}
