package com.url.shortening.url.shortening.datasvc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.url.shortening.base.datasvc.BaseRepoImpl;
import com.url.shortening.base.datasvc.IBaseRepo;
import com.url.shortening.url.shortening.common.UrlShorteningDto;
import com.url.shortening.url.shortening.common.UrlShorteningQueryDto;
import com.url.shortening.url.shortening.datasvc.IUrlShorteningDataSvc;
import com.url.shortening.url.shortening.datasvc.IUrlShorteningRepo;
import com.url.shortening.url.shortening.model.UrlShorteningMdl;

@Component
public class UrlShorteningDataSvc
		extends BaseRepoImpl<UrlShorteningDto, Long, UrlShorteningMdl, Long, UrlShorteningQueryDto>
		implements IUrlShorteningDataSvc {

	@Autowired
	IUrlShorteningRepo urlShorteningRepo;

	@Override
	protected Class<UrlShorteningMdl> convertedEntity() {
		return UrlShorteningMdl.class;
	}

	@Override
	protected Class<UrlShorteningDto> convertedDto() {
		return UrlShorteningDto.class;
	}

	@Override
	protected String paramForWhereClause(StringBuilder queryBuilder, UrlShorteningQueryDto dto) {
		if (dto.getFullUrl() != null && !dto.getFullUrl().isEmpty()) {
			queryBuilder.append(" where ");
			queryBuilder.append("full_url = " + "'" + dto.getFullUrl() + "'");

		}
		if (dto.getShortenedUrl() != null && !dto.getShortenedUrl().isEmpty()) {
			if (queryBuilder.toString().trim().contains("where")) {
				queryBuilder.append(" and  ");
				queryBuilder.append("shortened_url = " + "'" + dto.getShortenedUrl() + "'");

			} else {
				queryBuilder.append(" where ");
				queryBuilder.append("shortened_url = " + "'" + dto.getShortenedUrl() + "'");
			}

		}
		return " ; ";

	}

	@Override
	protected String createCriteriaQuery(StringBuilder queryBuilder) {
		return "select * from url ";
	}

	@Override
	protected IBaseRepo getRepo() {
		return urlShorteningRepo;
	}

}
