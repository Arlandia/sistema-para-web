package br.com.gelateria.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.TipoInsumoDao;
import br.com.gelateria.model.TipoInsumo;



public class TipoInsumoDaoJpa  extends DaoJpa<TipoInsumo , Integer> implements TipoInsumoDao{

	public TipoInsumoDaoJpa(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TipoInsumo> pegarNome(String nome) {
		String consulta = "select tp from TipoInsumo tp where tp.nome like :nome";
		TypedQuery<TipoInsumo> q = this.manager.createQuery(consulta,TipoInsumo.class);
		q.setParameter("nome","%" + nome +"%");
		return q.getResultList();
	}
	
	
	public List<TipoInsumo> pegarIdTipoÎnsumo(){
		String consulta ="select ti from TipoInsumo ti";
		TypedQuery<TipoInsumo> tq = manager.createQuery(consulta, TipoInsumo.class);
		 return tq.getResultList();
	}

	
	
	public List<Double> pegarQtdDeUmInsumo(){
		List<TipoInsumo> id = this.pegarIdTipoÎnsumo();
	
		List<Double> pd = new ArrayList<Double>();
	   for (TipoInsumo tp : id) {
		   String consulta="select SUM(i.pesoTotal) from Insumo i where i.tipoInsumo.codigo= :integer";
		   TypedQuery<Double> tq  = manager.createQuery(consulta, Double.class);
			 tq.setParameter("integer", tp.getCodigo());
			  pd.add(tq.getSingleResult());		
	}
	   
	   return pd;
		
	    
	    
		
	}

	


}
