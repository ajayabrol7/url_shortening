package com.url.shortening.url.shortening.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.url.shortening.base.dto.BaseErrorMetaData;
import com.url.shortening.base.factory.impl.BaseValidator;
import com.url.shortening.url.shortening.common.UpdateUrlShorteningRequest;
import com.url.shortening.url.shortening.common.UpdateUrlShorteningResponse;

@Component
public class UpdateUrlShorteningValidator
		extends BaseValidator<UpdateUrlShorteningRequest, UpdateUrlShorteningResponse> {

	@Override
	public UpdateUrlShorteningResponse validator(UpdateUrlShorteningRequest request) {

		// Regex to validate URLs
		String URL_REGEX = "^(https?:\\/\\/)?([\\w\\-]+\\.)+[\\w\\-]+(:\\d+)?(\\/\\S*)?$";
		Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
		String fullUrl = request.getQueryDto().get(0).getFullUrl();
		boolean urlFlag = isValidURL(fullUrl, URL_PATTERN);
		if (!urlFlag) {
			UpdateUrlShorteningResponse updResponse = new UpdateUrlShorteningResponse();
			List<BaseErrorMetaData> errors = new ArrayList<>();
			updResponse.setStatus("500");
			BaseErrorMetaData error = new BaseErrorMetaData();
			error.setErrorCode("CW_URL_VAL_FAILED_500");
			error.setErrorDescription("Error in url format");
			errors.add(error);
			updResponse.setErrors(errors);
			return updResponse;
		}
		UpdateUrlShorteningResponse updResponse = new UpdateUrlShorteningResponse();

		return updResponse;
	}

	private boolean isValidURL(String url, Pattern URL_PATTERN) {
		if (url == null || url.isEmpty())
			return false;
		Matcher matcher = URL_PATTERN.matcher(url);
		return matcher.matches();
	}

}
