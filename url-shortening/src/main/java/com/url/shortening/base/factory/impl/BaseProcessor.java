package com.url.shortening.base.factory.impl;

import com.url.shortening.base.dto.BaseRequest;
import com.url.shortening.base.dto.BaseResponse;
import com.url.shortening.base.factory.IProcessor;

public abstract class BaseProcessor<TRequest extends BaseRequest<?>, TResponse extends BaseResponse<?>>
		implements IProcessor<TRequest, TResponse> {

	@Override
	public TResponse process(TRequest request) {
		return execute(request);
	}

	public abstract TResponse execute(TRequest request);

}
