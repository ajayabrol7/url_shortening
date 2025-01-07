package com.url.shortening.base.dto;

import org.springframework.stereotype.Component;

/**
 * @param <TDto>
 */
@Component
public class BaseRequest<TQueryDto> {

	private boolean deleteFlag = false;

	private TQueryDto queryDto;

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public TQueryDto getQueryDto() {
		return queryDto;
	}

	public void setQueryDto(TQueryDto queryDto) {
		this.queryDto = queryDto;
	}
}