package com.url.shortening.base.factory.impl;

import com.url.shortening.base.dto.BaseRequest;
import com.url.shortening.base.dto.BaseResponse;
import com.url.shortening.base.factory.IValidator;

public abstract class BaseValidator<TRequest extends BaseRequest<?>, TResponse extends BaseResponse<?>>
		implements IValidator<TRequest, TResponse> {

	@Override
	public TResponse validate(TRequest request) {
		return validator(request);
	}

	public abstract TResponse validator(TRequest request);

}
