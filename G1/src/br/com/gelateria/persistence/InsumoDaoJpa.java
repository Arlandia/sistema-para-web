package br.com.gelateria.persistence;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.InsumoDao;
import br.com.gelateria.model.Fornecedor;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.TipoInsumo;

public class InsumoDaoJpa  extends DaoJpa<Insumo, Integer> implements InsumoDao {

	public InsumoDaoJpa(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long idCompraInsumo(int id) {
		String consulta ="select Count(i.codigo) from Insumo i where i.compra.codigo= :id ";
		TypedQuery<Long> tq = manager.createQuery(consulta, Long.class);
		tq.setParameter("id", id);
		return   tq.getSingleResult();
	}
	
	@Override
	public List<TipoInsumo> pegarIdTipoInsumo(){
		String consulta ="select ti from TipoInsumo ti";
		TypedQuery<TipoInsumo> tq = manager.createQuery(consulta, TipoInsumo.class);
		 return tq.getResultList();
	}
	
	@Override
	public List<Double> pegarQtdDeUmInsumo(){
		List<TipoInsumo> id = this.pegarIdTipoInsumo();
	
		List<Double> pd = new ArrayList<Double>();
	   for (TipoInsumo tp : id) {
		   String consulta="select SUM(i.pesoTotal) from Insumo i where i.tipoInsumo.codigo= :integer";
		   TypedQuery<Double> tq  = manager.createQuery(consulta, Double.class);
			 tq.setParameter("integer", tp.getCodigo());
			  pd.add(tq.getSingleResult());		
	}
	   
	   return pd;
				
	}
	
	@Override
	public double maiorValorMaior(){
		double mMValor =0;
	 for (int i = 0; i < this.pegarQtdDeUmInsumo().size(); i++) {
		    
		    if(this.pegarQtdDeUmInsumo().get(i)!=null){	    
		     if(mMValor < this.pegarQtdDeUmInsumo().get(i)){
		    	mMValor = this.pegarQtdDeUmInsumo().get(i); 
		    }
	     }
	 } 
	 return mMValor;
		}
		
	@Override
  public double  menorValorMenor(){
		
		double menorValor =this.maiorValorMaior();
	 for (int i = 0; i < this.pegarQtdDeUmInsumo().size(); i++) {
		 System.err.println(this.pegarQtdDeUmInsumo().get(i));
		    if(this.pegarQtdDeUmInsumo().get(i)!=null){	    
		     if(menorValor > this.pegarQtdDeUmInsumo().get(i)){
		    	menorValor = this.pegarQtdDeUmInsumo().get(i); 
		    }
	     }
	 } 
	 return menorValor;
		}
	
	@Override
  public Insumo  ValoInsumo(int id){
		
	String consulta="select i from Insumo i where i.tipoInsumo.codigo= :id";
	TypedQuery<Insumo> tq = manager.createQuery(consulta, Insumo.class);
	tq.setParameter("id", id);
	 return tq.getSingleResult();
	     
	 } 
	 
  
	@Override
	public int maiorValorInsumo(){
		int insumo = 0;
		double mMValor =0;
	 for (int i = 0; i < this.pegarQtdDeUmInsumo().size(); i++) {
		    
		    if(this.pegarQtdDeUmInsumo().get(i)!=null){	    
		     if(mMValor < this.pegarQtdDeUmInsumo().get(i)){
		    	mMValor = this.pegarQtdDeUmInsumo().get(i); 
		    	insumo=i+1;
		    }
	     }
	 } 
	 return insumo;
		}
	
	@Override	
  public int  menorValorInsumo(){
	int insumo = 0;
		double menorValor =this.maiorValorMaior();
	 for (int i = 0; i < this.pegarQtdDeUmInsumo().size(); i++) {
		    if(this.pegarQtdDeUmInsumo().get(i)!=null){	    
		     if(menorValor > this.pegarQtdDeUmInsumo().get(i)){
		    	menorValor = this.pegarQtdDeUmInsumo().get(i);
		    /*	String consulta="select i from Insumo i where i.codigo= :id";
		       TypedQuery<Insumo> tq = manager.createQuery(consulta, Insumo.class);
		        tq.setParameter("id", i);
		        insumo =tq.getSingleResult();*/
		    	insumo = i+1;
		    }
	     }
	 } 
	 return insumo;
		}
		
	
	
	
	

	@Override
	public int ultimoIdInsumo() {
		 String consulta ="Select i.codigo from Insumo i order by i.codigo desc";
		 TypedQuery<Integer> tq = manager.createQuery(consulta, Integer.class);
		 tq.setMaxResults(1);
		return tq.getSingleResult(); 
		
	}

	@Override
	public Insumo ultimoId() {
		 String consulta ="Select i from Insumo i order by i.codigo desc";
		 TypedQuery<Insumo> tq = manager.createQuery(consulta, Insumo.class);
		 tq.setMaxResults(1);
		return tq.getSingleResult(); 
	}

	@Override
	public List<Insumo> pegarNome(String nome) {
		String consulta = "select i from Insumo i where i.nome like :nome";
		TypedQuery<Insumo> q = this.manager.createQuery(consulta,Insumo.class);
		q.setParameter("nome","%" + nome +"%");
		return q.getResultList();
	}

	
	//para o grafico
	public Double qtaDeUmTipoInsumo(int id){
		List<Double> pd = new ArrayList<Double>();

		   String consulta="select SUM(i.pesoTotal) from Insumo i where i.tipoInsumo.codigo= :integer";
		   TypedQuery<Double> tq  = manager.createQuery(consulta, Double.class);
		  tq.setParameter("integer", id);
	        return tq.getSingleResult();		
	
	   
	 
				
	}
	
	
}
	
	
	
	


  

