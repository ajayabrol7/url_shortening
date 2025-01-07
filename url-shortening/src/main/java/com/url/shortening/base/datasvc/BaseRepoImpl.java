package com.url.shortening.base.datasvc;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.url.shortening.base.dto.BaseDto;
import com.url.shortening.base.dto.BaseModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public abstract class BaseRepoImpl<TDto extends BaseDto, TId, TModel extends BaseModel<TId>, TLong, TQueryDto>
		implements IBaseRepoMethod<TDto, TQueryDto, TModel, TLong> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<TDto> query(TQueryDto dto) {
		List<TDto> listDto = new ArrayList<TDto>();
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(createCriteriaQuery(queryBuilder));
		if (dto != null) {
			paramForWhereClause(queryBuilder, dto);
		}
		Query query = entityManager.createNativeQuery(queryBuilder.toString(), convertedEntity());
		System.out.println("Query :" + queryBuilder.toString());
		List<TModel> resultList = query.getResultList();
		for (TModel tModel : resultList) {
			ModelMapper modelMapper = new ModelMapper();
			TDto newDto = modelMapper.map(tModel, convertedDto());
			listDto.add(newDto);
		}
		return listDto;

	}

	@Override
	@Transactional
	public List<TDto> updateAllOrNone(List<TDto> listDtos) {
		List<TDto> dataSavedList = new ArrayList<>();
		int count = 0;
		try {
			System.out.println("Data size for inserted" + listDtos.size());
			for (TDto dto : listDtos) {
				ModelMapper modelMapper = new ModelMapper();
				if (dto.getId() != null) {
					TModel entity = modelMapper.map(dto, convertedEntity());

					entityManager.find(entity.getClass(), dto.getId());
					entityManager.merge(entity);
					count++;
					dataSavedList.add(dto);
				} else {
					TModel entity = modelMapper.map(dto, convertedEntity());
					entityManager.persist(entity);
					count++;
					dataSavedList.add(dto);
				}

			}
		} catch (Exception e) {
			System.out.println("Error While saving data");
		}
		System.out.println("Saving data completed" + count);
		return dataSavedList;
	}

	@Override
	@Transactional
	public List<TDto> deleteAllOrNone(List<TDto> listDtos, Class<TModel> entityClass) {
		List<TDto> dataDeleteList = new ArrayList<>();
		int count = 0;

		try {
			System.out.println("Data size for deletion: " + listDtos.size());

			for (TDto dto : listDtos) {
				// Convert DTO to Entity using ModelMapper
				ModelMapper modelMapper = new ModelMapper();
				TModel entity = modelMapper.map(dto, entityClass);

				if (entity != null) {
					// Find the entity in the database to ensure it's managed
					TModel managedEntity = entityManager.find(entityClass, entity.getId());

					if (managedEntity != null) {
						// Remove the managed entity
						entityManager.remove(managedEntity);
						count++;
						dataDeleteList.add(dto);
					} else {
						throw new EntityNotFoundException("Entity with ID " + entity.getId() + " not found.");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error while deleting data: " + e.getMessage());
			e.printStackTrace();
			throw e; // Ensure transaction rollback in case of an error
		}

		System.out.println("Deletion completed. Total deleted: " + count);
		return dataDeleteList;
	}

	@Override
	public void nativeQuery(String queryString) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(queryString);
		System.out.println("Native query :" + queryString);
		Query query = entityManager.createNativeQuery(queryBuilder.toString(), convertedEntity());
		query.executeUpdate();
		System.out.println("Native query execute successfully...");
	}

	protected Class<TModel> convertedEntity() {
		return null;
	}

	protected Class<TDto> convertedDto() {
		return null;
	}

	protected abstract String createCriteriaQuery(StringBuilder queryBuilder);

	protected IBaseRepo<TModel, TLong> getRepo() {
		return null;
	}

	protected abstract String paramForWhereClause(StringBuilder queryBuilder, TQueryDto dto);

}
