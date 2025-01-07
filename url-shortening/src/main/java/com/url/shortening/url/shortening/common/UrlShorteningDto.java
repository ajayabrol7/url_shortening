package com.url.shortening.url.shortening.common;

import com.url.shortening.base.dto.BaseDto;

public class UrlShorteningDto extends BaseDto<String> {

	private String id;

	private String fullUrl;

	private String shortenedUrl;

	private String countAttempts;

	private String shortUrlGenTime;

	private String lastModified;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getShortUrlGenTime() {
		return shortUrlGenTime;
	}

	public void setShortUrlGenTime(String shortUrlGenTime) {
		this.shortUrlGenTime = shortUrlGenTime;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "UrlShorteningDto [id=" + id + ", fullUrl=" + fullUrl + ", shortenedUrl=" + shortenedUrl
				+ ", countAttempts=" + countAttempts + ", shortUrlGenTime=" + shortUrlGenTime + ", lastModified="
				+ lastModified + "]";
	}

}
