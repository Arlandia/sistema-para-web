package br.com.gelateria.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.CargoDao;
import br.com.gelateria.model.Cargo;

public class CargoDaoJpa extends DaoJpa<Cargo, Integer> implements CargoDao {

	public CargoDaoJpa(EntityManager manager) {
		super(manager);
		
	}

	@Override
	public List<Cargo> pegarNome(String nome) {
		String consulta = "select c from Cargo c where c.nome like :nome";
		TypedQuery<Cargo> q = this.manager.createQuery(consulta,Cargo.class);
		q.setParameter("nome","%" + nome +"%");
		return q.getResultList();
				
	}

}
