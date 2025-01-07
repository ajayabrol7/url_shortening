package com.url.shortening.base.factory;

public interface IProcessor<TRequest, TResponse> {

	TResponse process(TRequest request);
}
