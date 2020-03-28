package com.todometer.service;

import java.util.List;

/**
 *
 * @author Miguel Tortosa Calabuig
 */
public interface GenericService<T,K> {
	
	List<T> getAll();

	T getById(K id);
	
	void add(T object);
	
	void delete(T object) throws Exception;
	
	void update(T object);

}