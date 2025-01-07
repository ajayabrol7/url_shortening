package com.url.shortening.url.shortening.model;

import com.url.shortening.base.dto.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "url", schema = "url_shortening")
public class UrlShorteningMdl extends BaseModel<Long> {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_url")
	private String fullUrl;

	@Column(name = "shortened_url")
	private String shortenedUrl;

	@Column(name = "count_attempts")
	private Long countAttempts;

	@Column(name = "shortened_url_gen_time")
	private String shortUrlGenTime;

	@Column(name = "last_modified")
	private String lastModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getCountAttempts() {
		return countAttempts;
	}

	public void setCountAttempts(Long countAttempts) {
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

}
