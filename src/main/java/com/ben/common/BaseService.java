package com.ben.common;

public interface BaseService<T> {
	Long save(T entity);

	Long deleteById(T entity);

	Long updateById(T entity);

	T selectById(String id);

	Integer selectByIndexCount(T entity);
}
