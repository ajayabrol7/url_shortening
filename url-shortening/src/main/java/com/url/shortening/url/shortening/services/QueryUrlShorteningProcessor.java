package com.url.shortening.url.shortening.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.url.shortening.base.factory.impl.BaseProcessor;
import com.url.shortening.url.shortening.common.QueryUrlShorteningRequest;
import com.url.shortening.url.shortening.common.QueryUrlShorteningResponse;
import com.url.shortening.url.shortening.common.UrlShorteningDto;
import com.url.shortening.url.shortening.datasvc.IUrlShorteningDataSvc;

@Component
public class QueryUrlShorteningProcessor extends BaseProcessor<QueryUrlShorteningRequest, QueryUrlShorteningResponse> {

	@Autowired
	IUrlShorteningDataSvc urlShorteningDataSvc;

	@Override
	public QueryUrlShorteningResponse execute(QueryUrlShorteningRequest request) {

		List<UrlShorteningDto> queryResponse = urlShorteningDataSvc.query(request.getQueryDto());
		QueryUrlShorteningResponse response = new QueryUrlShorteningResponse();
		response.setDataList(queryResponse);
		return response;
	}

}
