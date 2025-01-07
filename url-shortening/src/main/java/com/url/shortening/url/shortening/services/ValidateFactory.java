package com.url.shortening.url.shortening.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.url.shortening.base.dto.BaseRequest;
import com.url.shortening.base.factory.IValidator;
import com.url.shortening.base.factory.IValidatorFactory;
import com.url.shortening.base.factory.impl.BaseValidator;
import com.url.shortening.url.shortening.common.UpdateUrlShorteningRequest;
import com.url.shortening.url.shortening.common.UpdateUrlShorteningResponse;

@Component
public class ValidateFactory implements IValidatorFactory {

	@Autowired
	BaseValidator<UpdateUrlShorteningRequest, UpdateUrlShorteningResponse> updateUrlShorteningValidator;

	@Override
	public IValidator getValidator(BaseRequest request) {
		if (request instanceof UpdateUrlShorteningRequest) {
			return updateUrlShorteningValidator;
		} else {
			return null;
		}
	}

}
