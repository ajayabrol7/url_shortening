package com.url.shortening.url.shortening.common;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class UrlShorteningQueryDto {

	@JsonProperty("fullUrl")
	private String fullUrl;

	@JsonProperty("shortenedUrl")
	private String shortenedUrl;

	@JsonProperty("countAttempts")
	private String countAttempts;

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getShortenedUrl() {
		return shortenedUrl;
	}

	public void setShortenedUrl(String shortenedUrl) {
		this.shortenedUrl = shortenedUrl;
	}

	public String getCountAttempts() {
		return countAttempts;
	}

	public void setCountAttempts(String countAttempts) {
		this.countAttempts = countAttempts;
	}

}
