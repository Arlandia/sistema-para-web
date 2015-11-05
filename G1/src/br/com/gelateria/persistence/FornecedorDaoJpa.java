package br.com.gelateria.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.FornecedorDao;
import br.com.gelateria.model.Cargo;
import br.com.gelateria.model.Fornecedor;

public class FornecedorDaoJpa  extends DaoJpa<Fornecedor , Integer> implements FornecedorDao {

	public FornecedorDaoJpa(EntityManager manager) {
		super(manager);
		
	}
	
	
	@Override
	public List<Fornecedor> pegarNome(String nome) {
		String consulta = "select c from Fornecedor c where c.nome like :nome";
		TypedQuery<Fornecedor> q = this.manager.createQuery(consulta,Fornecedor.class);
		q.setParameter("nome","%" + nome +"%");
		return q.getResultList();
				
	}

}
