package br.com.gelateria.teste;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.Select;
import org.hibernate.transform.Transformers;

import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Cargo;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.TipoInsumo;

public class ClasseTestada  extends PersistenceTestada {

	
	

	
	private  Session session;
	 
	 public ClasseTestada(){
		 session = (Session) this.manager.getDelegate(); 
	 }
	
	
	/*public Cargo pega(String nome) {
		System.out.println(nome);
		String consulta = "select c from Cargo c where c.nome like :nome";
		TypedQuery<Cargo> q = this.manager.createQuery(consulta,Cargo.class);
		q.setParameter("nome","%" + nome +"%");
		return q.getSingleResult();
		
				
	}*/
	
	
	
	//Criteria
/*	public void ct(){
	CriteriaBuilder builder = this.manager.getCriteriaBuilder();
	CriteriaQuery<Insumo> cq =builder.createQuery(Insumo.class);
	Root<Insumo> c = cq.from(Insumo.class);
	cq.select(c);
	cq.where(builder.like(c.<String>get("nome"),"Sab%"));
	List<Insumo> insumo = this.manager.createQuery(cq).getResultList();
	
	for(Insumo i : insumo){
		System.out.println(i.getNome());
	}
	
	}*/
  /*public void pegaResultado(){
	  //pega apenas dois resultados
	Criteria c = session.createCriteria(Insumo.class).setMaxResults(2);
	List<Insumo> list = new ArrayList<Insumo>();
	list = c.list();
	for(Insumo i : list){
		System.err.println(i.getNome());
	}
  }
	*/
	/* public void Restricao1(){
		  //pega usando Restricao pela primeira letra
		Criteria c = session.createCriteria(Alerta.class).add(Restrictions.like("codigo_alerta", "C%"));
		List<Alerta> list = new ArrayList<Alerta>();
		System.err.println(list.toString());
		list = c.list();
		for(Alerta i : list){
			System.err.println(i.getNome());
			System.err.println(i.getCodigo_alerta());
		}
	 }*/
	 
	/* 
	 public void pp(){
	   String consultaCompra ="Select c from Compra c ";
	   String consultaInsumo ="Select i from Insumo i join i.compra cmp ";
	   System.out.println(consultaInsumo);
	   TypedQuery<Compra> tqc= this.manager.createQuery(consultaCompra,Compra.class);
	   TypedQuery<Insumo> tqi = this.manager.createQuery(consultaInsumo, Insumo.class);

	  List<Compra> lc= tqc.getResultList();
	  List<Insumo> li = tqi.getResultList();
	   
	   for(Compra i : lc){
		   System.out.println(i.getNumeroCupom());
	   }
	   
	   for(Insumo c : li ){
		   System.out.println(c.getCodigo());
		   
	   }
	   
	 }
	 
	 
	 
	 public void Restricao2(){
		  //pega usando Restricao pcom duas tabelas trazendo os insumo de codigo 1
		Criteria c = session.createCriteria(Insumo.class);
		c.add(Restrictions.like("nome", "S", MatchMode.START));
		c.createAlias("compra", "cmp").add(Restrictions.eq("cmp.codigo", 1));
		
		List<Insumo> list = new ArrayList<Insumo>();
		list = c.list();
		

		System.err.println(list.toString());

		for(Insumo i : list){
			System.err.println(i.getNome());
			
		}
		
	
	 }*/
	 
	/* public void Restricao2(){
		  //pega usando Restricao pcom duas tabelas trazendo os insumo de codigo 1
		Criteria c = session.createCriteria(Compra.class);
		c.createAlias("insumo", "i")
		.add(Restrictions.eq("codigo", "i.compra_id"));
		c.setProjection(Projections.projectionList()
				.add( Projections.property("itens"), "itens" )
				.add( Projections.property("numeroCupom"), "numeroCupom" )
				
				);
		
		  
	c.setResultTransformer(Transformers.aliasToBean(Compra.class));
       List<Compra> list = new ArrayList<Compra>();
        list=  c.list();
		
        for(Compra i : list){
        	System.err.println(i.getItens());
        	System.err.println(i.getNumeroCupom());
        }
        
        System.err.println();
		System.err.println(c.toString());
	    
		
 /*
		  * esta linha compara o codigo de compra na tabela insumo que é menor em codigo
		 criteria.add(Restrictions.ltProperty("compra","cmp.codigo"));
		 */
		 
		/* 
		 * Criteria criteriaCompra = criteria.createCriteria("compra","cmp")	 
				 .add(Restrictions.eq("cmp.codigo","ins.compra_id"));		
		 Criteria cC= criteria.createCriteria("compra");
		 cC.add(Restrictions.eq("codigo", 1));
	    
	
		
		
		
		
	
	 }*/
	 
	 
	 
	 
	/* 
	 public void Criterio(){
		 Criteria criteria = session.createCriteria(Insumo.class);
			criteria.createAlias("compra", "cmp").add(Restrictions.eqProperty("cmp.codigo", "compra"));
		 List<Insumo> list = new ArrayList<Insumo>();
			list = criteria.list();
			

			System.out.println(list.toString());
			
			for(Insumo i : list){
				System.out.println(i.getNome());
				System.out.println(i.getCompra().getCodigo());
				System.out.println(i.getCompra().getItens());
			}
			
	
		 
	 }*/
	 
	 
	  public void nCupom(){
		  Criteria c = session.createCriteria(Compra.class);
		  c.add(Restrictions.eq("codigo","listaInsumos"));
		 /* List<Compra> cmp = c.list();
		  
		  for(Compra cp : cmp){
			  System.err.println();
		  }*/
		  
		  
	  }
	  
	  
	  public void nCupomJpql(){
		 /* //select com join de insumo utilizando e  compra codigo diferente de 1
		  String consulta = "Select i from  Insumo i join i.compra icmp where icmp.itens <>'0'";
		   Query tqc= this.manager.createQuery(consulta, Insumo.class); 
		  */
		  Compra compra = new Compra();
		  Insumo ins = new Insumo();
		  String consulta ="select icmp.itens from Insumo i join i.compra icmp where icmp.codigo= :1";
		  Query tqc = this.manager.createQuery(consulta, Number.class);
		  tqc.setParameter("codigo", 1);
		  
		  
		  
		  /*List<Insumo>  cco=   tqc.getResultList();  
		  for(Insumo cmp : cco){
		     System.err.println(cmp.getCompra().getCodigo());
		     System.err.println(cmp.getCompra().getNumeroCupom());
		     System.err.println(cmp.getCompra().getItens());*/
			  
	       }
	  
	  
	  //pegando o ultimo id e recuperando a ultima compra
	  public String pegarUltimoIdColaborador() {
			String consulta ="Select c.numeroCupom from Compra c order by c.codigo desc";
			TypedQuery<String> tq =  manager.createQuery(consulta, String.class);
			tq.setMaxResults(1);			
			return tq.getSingleResult();
		}
	  
	  
		public Long idCompraInsumo(int id) {
			String consulta ="select Count(i.codigo) from Insumo i where i.compra.codigo= :id ";
			TypedQuery<Number> tq = manager.createQuery(consulta, Number.class);
			tq.setParameter("id", id);
			return  (Long) tq.getSingleResult();
		}
	  
	     
	 



public long pegarMaiorQtaInsumo(){
	String consultaUltimoId ="select Count(tp.codigo) from TipoInsumo tp";
	TypedQuery<Long> tq = manager.createQuery(consultaUltimoId, Long.class);
	return tq.getSingleResult();
}

public Double pegarM(){
	int id =1;
	String consultaUltimoId ="select SUM(i.pesoTotal) from Insumo i where i.tipoInsumo.codigo= :id";
	TypedQuery<Double> tq = manager.createQuery(consultaUltimoId, Double.class);
	tq.setParameter("id", id);
	 
	return tq.getSingleResult();
}



public double maior(){
	double m = this.pegarMaiorQtaInsumo();
	String consulta;
	int interacao[] = new int[(int)m];
	double valor[]  = new double[ (int) m];
	double maiorValor = valor[0];
	int idUltimo=0;
	//for para as consultas
	for (int i = 0; i < interacao.length; i++) {
		int verifica = i+1;	
		int novo =this.verifcaId(verifica);
		int id = i+novo;
		if(id <=i+1){
		consulta = "select SUM(i.pesoTotal) from Insumo i where i.tipoInsumo.codigo= :id";
		TypedQuery<Double> tq = manager.createQuery(consulta,Double.class);
		tq.setParameter("id", id);
		
      System.out.println(tq.toString());
	  System.out.println("id"+id); 
	  System.err.println("resultado"+tq.getSingleResult());
	  
	  valor[i]= tq.getSingleResult();
		}
	  
	}
	//for para pegar o maior valor
	  for (int j = 0; j < valor.length; j++) {
			if(valor[j] > maiorValor){
				maiorValor = valor[j];
				idUltimo = j+1;
			} 	
	}
	  System.out.println("ultimo"+idUltimo);
	  System.out.println("mv"+maiorValor);
	this.insumoQueTemMais(idUltimo);
	return maiorValor;
}

 public  String insumoQueTemMais(int id){
	 String consulta ="select tp.nome from TipoInsumo tp where tp.codigo= :id";
	 TypedQuery<String> tp = manager.createQuery(consulta,String.class );
	 tp.setParameter("id", id);
	 return tp.getSingleResult();
	 
 }
 public int verifcaId(int verificaId){
	 String consulta="select SUM(i.pesoTotal) from Insumo i where i.tipoInsumo.codigo= :id";
	 TypedQuery<Double> tq= manager.createQuery(consulta,Double.class);
	 tq.setParameter("id", verificaId);
	 if(tq.getSingleResult()==null){
		 System.out.println("return 2");
		 return 2;
	 }
	 System.out.println("return 1");
	 return 1;
	 
 }
 
 
 
 
 


}