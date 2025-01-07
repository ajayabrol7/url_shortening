package com.url.shortening.base.datasvc;

import java.util.List;

public interface IBaseRepoMethod<TDto, TQueryDto, TModel, TLong> {

	List<TDto> query(TQueryDto dto);

	List<TDto> updateAllOrNone(List<TDto> listDtos);

	void nativeQuery(String query);

	List<TDto> deleteAllOrNone(List<TDto> listDtos, Class<TModel> entityClass);
}
