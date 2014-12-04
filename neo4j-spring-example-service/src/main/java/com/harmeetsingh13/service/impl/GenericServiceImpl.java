/**
 * 
 */
package com.harmeetsingh13.service.impl;

import java.lang.reflect.Type;

import org.springframework.core.GenericTypeResolver;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.harmeetsingh13.service.GenericService;

/**
 * @author Harmeet Singh(Taara)
 *
 */

@SuppressWarnings("unchecked")
public class GenericServiceImpl<T, R extends GraphRepository<T>> implements GenericService<T>{

	private Class<R>[] type;
	private Type genericType;
	private R repository;
	
	public GenericServiceImpl() {
		try{
			type = (Class<R>[]) GenericTypeResolver.resolveTypeArguments(getClass(), GenericServiceImpl.class);
			genericType = type[0];
			repository = type[1].newInstance();
		}catch(Exception ex){
			
		}
	}
	
	@Override
	public T save(T entity) {
		return repository.save(entity);
	}
}
