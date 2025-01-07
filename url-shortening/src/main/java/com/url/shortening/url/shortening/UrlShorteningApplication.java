package com.url.shortening.url.shortening;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.url.shortening")
public class UrlShorteningApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShorteningApplication.class, args);
	}

}
