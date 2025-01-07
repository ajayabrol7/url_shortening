package com.url.shortening.base.factory;

public interface IValidator<TRequest, TResponse> {

	TResponse validate(TRequest request);
}
