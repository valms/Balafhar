package main.java.br.com.valmarjunior.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDAO<T, ID extends Serializable> {
	
	public T save(T entity);
	
	public void remove(T entity);
	
	public T getById(Class<T> entityClass, ID pk);
	
	public List<T> getAll(Class<T> entityClass);
}
