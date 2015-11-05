package br.com.gelateria.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface Dao<T,I extends Serializable >  {
 void save(T entity);
 void remove(Class<T> classe, I pk);
 T getById(Class<T> classe, I pk);
 List<T> getAll(Class<T> classe);




	
}
