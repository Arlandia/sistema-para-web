package br.com.gelateria.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.FabricacaoDao;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Fabricacao;

public class FabricacaoDaoJpa extends DaoJpa<Fabricacao,Integer> implements FabricacaoDao {

	public FabricacaoDaoJpa(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fabricacao pegarUltimoId() {
		String consulta ="Select fb From Fabricacao fb order by fb.codigo desc";
		TypedQuery<Fabricacao> tp = manager.createQuery(consulta, Fabricacao.class);
		tp.setMaxResults(1);
		return tp.getSingleResult();
	}
	
	public List<Fabricacao> buscarTresUltimoFabricacao() {
		String consulta ="Select fb From Fabricacao fb order by fb.codigo desc";
		TypedQuery<Fabricacao> tp = manager.createQuery(consulta, Fabricacao.class);
		tp.setMaxResults(3);
		return  tp.getResultList();
	}

	@Override
	public double fabricacaoTotalSorvete(String tipoProduto) {
		String consulta ="select SUM(f.volumeTotal) from Fabricacao f where f.tipoProduto.nome= :tipoProduto";
		TypedQuery<Double> tq = manager.createQuery(consulta, Double.class);
		tq.setParameter("tipoProduto", tipoProduto);
		return tq.getSingleResult();
	}

	@Override
	public List<Fabricacao> pegarNome(String nome) {
		String consulta ="select f from Fabricacao f where f.tipoProduto.nome like :nome";
		 TypedQuery<Fabricacao> tq = manager.createQuery(consulta,Fabricacao.class );
		 tq.setParameter("nome","%" + nome + "%");
	 		return tq.getResultList();	
	}

}
