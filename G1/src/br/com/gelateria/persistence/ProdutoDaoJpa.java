package br.com.gelateria.persistence;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.ProdutoDao;
import br.com.gelateria.model.Produto;

public class ProdutoDaoJpa  extends DaoJpa<Produto, Integer> implements ProdutoDao {

	public ProdutoDaoJpa(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Produto> tresUltimasProducao() {
		String consulta ="select p from Produto p order by p.codigo desc";
		TypedQuery< Produto> tq = manager.createQuery(consulta,Produto.class);
		tq.setMaxResults(3);
		return tq.getResultList();
	}

	@Override
	public Produto ultimaProducao() {
		String consulta ="select p from Produto p order by p.codigo desc";
		TypedQuery< Produto> tq = manager.createQuery(consulta,Produto.class);
		tq.setMaxResults(1);
		return tq.getSingleResult();
	}

	@Override
	public Produto totalProducaoOntem() {
		
		return null;
	}

	@Override
	public List pegarQta(double quantidade) {
		 String consulta ="select p from Produto p where p.quantidade= :quantidade";
		 TypedQuery< Produto> tq = manager.createQuery(consulta,Produto.class);
		 tq.setParameter("quantidade", quantidade);
		return tq.getResultList();
	}

}
