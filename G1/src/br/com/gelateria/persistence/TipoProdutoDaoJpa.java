package br.com.gelateria.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.TipoProdutoDao;
import br.com.gelateria.model.TipoInsumo;
import br.com.gelateria.model.TipoProduto;

public class TipoProdutoDaoJpa  extends DaoJpa<TipoProduto, Integer> implements TipoProdutoDao{

	public TipoProdutoDaoJpa(EntityManager manager) {
		super(manager);
		
	}

	
	@Override
	public String pegarDadosParaUmTipoProduto(int id) {
	 String Consulta="Select tp.nome From TipoProduto  tp where  tp.nome= :id";
	 TypedQuery<String>  tq = manager.createQuery(Consulta, String.class); 
	 tq.setParameter("id", id);
	 return tq.getSingleResult();
	}


	@Override
	public List<TipoProduto> pegarNome(String nome) {
		String consulta = "select tp from TipoProduto tp where tp.nome like :nome";
		TypedQuery<TipoProduto> q = this.manager.createQuery(consulta,TipoProduto.class);
		q.setParameter("nome","%" + nome +"%");

		return q.getResultList();
	}
	
	
	
}
