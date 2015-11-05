package br.com.gelateria.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.SaboresDao;
import br.com.gelateria.model.Sabores;

public class SaboresDaoJpa extends DaoJpa<Sabores , Integer> implements SaboresDao {

	public SaboresDaoJpa(EntityManager manager) {
		super(manager);
	
	}

	@Override
	public List<Sabores> buscarNome(String nome) {
		String consulta ="select s from Sabores s where s.nome like :nome";
		TypedQuery<Sabores> tq =this.manager.createQuery(consulta,Sabores.class);
		tq.setParameter("nome", "%" + nome +"%");
        return tq.getResultList();

	}

}
