	package br.com.gelateria.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.gelateria.dao.Dao;
@Repository
public class DaoJpa<T ,I extends Serializable> implements Dao<T,I> {
	
	@PersistenceContext
	protected EntityManager manager;
	
	 public DaoJpa(EntityManager tmanager) {
		 this.manager= tmanager;
    }
	 

	
	 

	@Override
	public void save(T entity) {
		this.manager.merge(entity);
		
	}

	@Override
	public void remove(Class<T> classe, I pk) {
	 T remover=	 this.getById(classe, pk);
	  this.manager.remove(remover);
		
	}

	@Override
	public T getById(Class<T> classe, I pk) {
		try{
			return this.manager.find(classe, pk);
		}catch(Exception exception){
			return null;
		}
		
	}

	@Override
	public List<T> getAll(Class<T> classe) {
		Query q = this.manager.createQuery("select x from "
				+ classe.getSimpleName() + " x");
				return q.getResultList();
	}


}
