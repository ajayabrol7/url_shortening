package com.url.shortening.base.dto;

import org.springframework.stereotype.Component;

@Component
public class BaseErrorMetaData {

	private String errorCode;

	private String errorMessage;

	private String errorDescription;

	private Long errorIndex;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Long getErrorIndex() {
		return errorIndex;
	}

	public void setErrorIndex(Long errorIndex) {
		this.errorIndex = errorIndex;
	}

}
