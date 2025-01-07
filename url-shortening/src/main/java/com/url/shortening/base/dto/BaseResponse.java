package com.url.shortening.base.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BaseResponse<TDto> {
	private String status;

	private List<BaseErrorMetaData> errors = new ArrayList<>();

	private TDto data;

	private List<TDto> dataList = new ArrayList<>();

	private Long count = null;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BaseErrorMetaData> getErrors() {
		return errors;
	}

	public void setErrors(List<BaseErrorMetaData> errors) {
		this.errors.addAll(errors);
	}

	public TDto getData() {
		return data;
	}

	public void setData(TDto data) {
		this.data = data;
	}

	public List<TDto> getDataList() {
		return dataList;
	}

	public void setDataList(List<TDto> dataList) {
		this.dataList.addAll(dataList);
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
