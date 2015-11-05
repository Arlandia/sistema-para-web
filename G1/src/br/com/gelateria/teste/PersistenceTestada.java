package br.com.gelateria.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceTestada {

	
	 
	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema");
	 EntityManager manager = emf.createEntityManager(); 
}
