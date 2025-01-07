package com.url.shortening.url.shortening.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.url.shortening.base.dto.BaseRequest;
import com.url.shortening.base.factory.IProcessor;
import com.url.shortening.base.factory.IProcessorFactory;
import com.url.shortening.base.factory.impl.BaseProcessor;
import com.url.shortening.url.shortening.common.QueryUrlShorteningRequest;
import com.url.shortening.url.shortening.common.QueryUrlShorteningResponse;
import com.url.shortening.url.shortening.common.UpdateUrlShorteningRequest;
import com.url.shortening.url.shortening.common.UpdateUrlShorteningResponse;

@Component
public class ProcessorFactory implements IProcessorFactory {

	@Autowired
	BaseProcessor<QueryUrlShorteningRequest, QueryUrlShorteningResponse> queryUrlShorteningProcessor;

	@Autowired
	BaseProcessor<UpdateUrlShorteningRequest, UpdateUrlShorteningResponse> updateUrlShorteningProcessor;

	@Override
	public IProcessor getProcessor(BaseRequest request) {
		if (request instanceof QueryUrlShorteningRequest) {
			return queryUrlShorteningProcessor;
		} else if (request instanceof UpdateUrlShorteningRequest) {
			return updateUrlShorteningProcessor;
		} else {
			return null;
		}
	}

}
