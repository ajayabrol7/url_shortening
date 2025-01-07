package com.url.shortening.url.shortening.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.url.shortening.base.factory.impl.BaseProcessor;
import com.url.shortening.url.shortening.common.UpdateUrlShorteningRequest;
import com.url.shortening.url.shortening.common.UpdateUrlShorteningResponse;
import com.url.shortening.url.shortening.common.UrlShorteningDto;
import com.url.shortening.url.shortening.common.UrlShorteningQueryDto;
import com.url.shortening.url.shortening.datasvc.IUrlShorteningDataSvc;
import com.url.shortening.url.shortening.model.UrlShorteningMdl;
import com.url.shortening.url.shortening.util.ShortUrlConversionUtil;

@Component
public class UpdateUrlShorteningProcessor
		extends BaseProcessor<UpdateUrlShorteningRequest, UpdateUrlShorteningResponse> {

	@Autowired
	IUrlShorteningDataSvc urlShorteningDataSvc;

	@Autowired
	ShortUrlConversionUtil shortUrlUtil;

	@Override
	public UpdateUrlShorteningResponse execute(UpdateUrlShorteningRequest request) {
		if (request.isDeleteFlag()) {
			List<UrlShorteningDto> updateResponse = urlShorteningDataSvc.deleteAllOrNone(request.getQueryDto(),
					UrlShorteningMdl.class);
			UpdateUrlShorteningResponse response = new UpdateUrlShorteningResponse();
			response.setDataList(updateResponse);
			return response;
		} else {
			String fullUrl = request.getQueryDto().get(0).getFullUrl();
			UrlShorteningQueryDto queryDto = new UrlShorteningQueryDto();
			queryDto.setFullUrl(fullUrl);
			List<UrlShorteningDto> urlQuery = urlShorteningDataSvc.query(queryDto);
			UrlShorteningMdl mdl = new UrlShorteningMdl();
			if (!urlQuery.isEmpty()) {
				ModelMapper modelMapper = new ModelMapper();
				mdl = modelMapper.map(urlQuery.get(0), UrlShorteningMdl.class);

			}
			if (urlQuery.isEmpty()) {
				String shortenedUrl = shortUrlUtil.shortenURL(fullUrl);
				if (request.getQueryDto().get(0).getCountAttempts() == null) {
					request.getQueryDto().get(0).setCountAttempts("1");
				}
				request.getQueryDto().get(0).setShortenedUrl(" https://www.shortenedurl/" + shortenedUrl);
				List<UrlShorteningDto> updateResponse = urlShorteningDataSvc.updateAllOrNone(request.getQueryDto());
				UpdateUrlShorteningResponse response = new UpdateUrlShorteningResponse();
				response.setDataList(updateResponse);
				return response;
			} else {

				if (mdl.getCountAttempts() == null && request.getQueryDto().get(0).getCountAttempts() == null) {
					request.getQueryDto().get(0).setCountAttempts("1");
				} else {
					request.getQueryDto().get(0).setCountAttempts(
							String.valueOf(Integer.valueOf((urlQuery.get(0).getCountAttempts())) + 1));
				}

				if (mdl.getFullUrl() == null && request.getQueryDto().get(0).getFullUrl() == null) {
					request.getQueryDto().get(0).setFullUrl(null);
				} else if (mdl.getFullUrl() == null && request.getQueryDto().get(0).getFullUrl() != null) {
					request.getQueryDto().get(0).setFullUrl(request.getQueryDto().get(0).getFullUrl());
				} else {
					request.getQueryDto().get(0).setFullUrl(mdl.getFullUrl());
				}

				if (mdl.getShortenedUrl() == null && request.getQueryDto().get(0).getShortenedUrl() == null) {
					request.getQueryDto().get(0).setShortenedUrl(null);
				} else if (mdl.getShortenedUrl() == null && request.getQueryDto().get(0).getShortenedUrl() != null) {
					request.getQueryDto().get(0).setShortenedUrl(request.getQueryDto().get(0).getShortenedUrl());
				} else {
					request.getQueryDto().get(0).setShortenedUrl(mdl.getShortenedUrl());
				}

				if (mdl.getLastModified() == null && request.getQueryDto().get(0).getLastModified() == null) {
					request.getQueryDto().get(0).setLastModified(null);
				} else if (mdl.getLastModified() == null && request.getQueryDto().get(0).getLastModified() != null) {
					request.getQueryDto().get(0).setLastModified(request.getQueryDto().get(0).getLastModified());
				} else {
					request.getQueryDto().get(0).setLastModified(mdl.getLastModified());
				}

				if (mdl.getShortUrlGenTime() == null && request.getQueryDto().get(0).getShortUrlGenTime() == null) {
					request.getQueryDto().get(0).setShortUrlGenTime(null);
				} else if (mdl.getShortUrlGenTime() == null
						&& request.getQueryDto().get(0).getShortUrlGenTime() != null) {
					request.getQueryDto().get(0).setShortUrlGenTime(request.getQueryDto().get(0).getShortUrlGenTime());
				} else {
					request.getQueryDto().get(0).setShortUrlGenTime(mdl.getShortUrlGenTime());
				}

				if (mdl.getId() == null && request.getQueryDto().get(0).getId() == null) {
					request.getQueryDto().get(0).setId(null);
				} else if (mdl.getId() == null && request.getQueryDto().get(0).getId() != null) {
					request.getQueryDto().get(0).setId(request.getQueryDto().get(0).getId());
				} else {
					request.getQueryDto().get(0).setId(String.valueOf(mdl.getId()));
				}

				List<UrlShorteningDto> updateResponse = urlShorteningDataSvc.updateAllOrNone(request.getQueryDto());
				UpdateUrlShorteningResponse response = new UpdateUrlShorteningResponse();
				response.setDataList(updateResponse);
				return response;

			}

		}
	}

}
