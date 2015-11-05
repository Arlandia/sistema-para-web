package br.com.gelateria.teste;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import sun.util.locale.ParseStatus;
import br.com.gelateria.model.Colaborador;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.TipoInsumo;
import br.com.gelateria.model.seguranca.Autorizacao;
import br.com.gelateria.model.seguranca.Usuario;

@SuppressWarnings("deprecation")
public class ClasseTestada_2  extends PersistenceTestada{
	
	private  Session session;
	private SessionFactory sessionFactory;
	 
	 public ClasseTestada_2(){
		 session = (Session) this.manager.getDelegate(); 
	 }

	 
	 public Compra pegarUltimoIdCompra() {
			String consulta ="Select c from Compra c order by c.codigo desc";
			TypedQuery<Compra> tq =  manager.createQuery(consulta,Compra.class);
			tq.setMaxResults(1);
			return tq.getSingleResult();
		}

	/* public void  testa(){
	 
	 try {
		 AnnotationConfiguration ac = new AnnotationConfiguration();
		
		 ac.addAnnotatedClass(Autorizacao.class);
		 ac.addAnnotatedClass(Usuario.class);
		 sessionFactory = ac.configure().buildSessionFactory();
		 SchemaExport se = new SchemaExport(ac);
		 se.create(true, true);
		 }catch (Throwable ex) {
		 System.err.println( ex);
		 throw new ExceptionInInitializerError(ex);
		 }
	 }*/

	//buscando o menor e maior iten do estoque insumo.  
	
/*	public long qtaDeIdEmTipoInsumo() {
			String consultaUltimoId ="select Count(tp.codigo) from TipoInsumo tp";
			TypedQuery<Long> tq = manager.createQuery(consultaUltimoId, Long.class);
			return tq.getSingleResult();	
	}
//o maior valor de valor no estoque de insumo
	
	public double maiorQtaDeUmInsumo(int id) {	
			int novo =this.verifcaId(id);
			int idId = id+novo;
			if(this.qtaDeIdEmTipoInsumo()>=idId){
			String consulta = "select SUM(i.pesoTotal) from Insumo i where i.tipoInsumo.codigo= :id";
			TypedQuery<Double> tq = manager.createQuery(consulta,Double.class);
			tq.setParameter("id", idId);
			
	    //  System.out.println(tq.toString());
		//  System.out.println("id"+id); 
		  //System.err.println("resultado"+tq.getSingleResult());
		   
 		   return  tq.getSingleResult();
			}
			int anterior = id-1;
			String consulta = "select SUM(i.pesoTotal) from Insumo i where i.tipoInsumo.codigo= :id";
			TypedQuery<Double> tq = manager.createQuery(consulta,Double.class);
			tq.setParameter("id", anterior);
			return  tq.getSingleResult();
			}
		

		
	public double maiorValor(){
		double m = this.qtaDeIdEmTipoInsumo();
		int valor[] = new int[(int) m];
		double maiorValor;
		double recebeMaiorValor = 0;
		  for (int j = 0; j < valor.length; j++) {
			  maiorValor = this.maiorQtaDeUmInsumo(j+1);
			  System.err.println("recebendo maior valor"+maiorValor+"j:"+j);
				if( maiorValor > recebeMaiorValor){
				    recebeMaiorValor = maiorValor;
				    System.err.println("recebe maior valor :"+recebeMaiorValor);
				    System.err.println("-- maior valor -- :"+maiorValor);
					
				} 
				
		}
		  return recebeMaiorValor;
	}
	
	
	public double menorValor(){
		double m = this.qtaDeIdEmTipoInsumo();
		int valor[] = new int[(int) m];
		double menorValor;
		double recebeMenorValor = this.maiorQtaDeUmInsumo(1);
		 System.err.println("primeira vez que recebe o valor:"+recebeMenorValor);
		  for (int j = 0; j < valor.length; j++) {
			  menorValor = this.maiorQtaDeUmInsumo(j+1);
				if( menorValor < recebeMenorValor){
				    recebeMenorValor = menorValor;
				    System.err.println("recebe menor vale:"+recebeMenorValor+"indice:"+j+1);
					
				} 
				
		}
		  return recebeMenorValor;
	}
	
	
	
	public int maiorValorId(){
		System.err.println("estou no metodo maiorValorId");
		double m = this.qtaDeIdEmTipoInsumo();
		int valor[] = new int[(int) m];
		double maiorValorId;
		double recebeMaiorValorId = 0;
		int maiorId=0;
		int verifica=0;
		  for (int j = 0; j < valor.length; j++) {
			  maiorValorId = this.maiorQtaDeUmInsumo(j+1);
			  System.err.println(" maior valor :"+maiorValorId+"j:"+j );
				if( maiorValorId > recebeMaiorValorId){
					  recebeMaiorValorId = maiorValorId;
					  System.err.println("recebeMaiorValorId :"+recebeMaiorValorId );
					  verifica = this.verifcaId(j+1);
				    maiorId = j+1+verifica;
				    System.err.println("j :"+j);
				    System.err.println("pegando id do maior valor :"+maiorId);
					
				} 
				
		}
		  return maiorId;
	}
	
	public int menorValorId(){
		double m = this.qtaDeIdEmTipoInsumo();
		int valor[] = new int[(int) m];
		System.err.println("m :"+m);
		double menorValorId;
		double recebeMenorValorId = this.maiorQtaDeUmInsumo(1);
		int menorId=1;
		int verifica =0;
		  for (int j = 0; j < valor.length; j++) {
			  System.err.println("j dentro do for:"+j);
			  menorValorId = this.maiorQtaDeUmInsumo(j+1);
			  System.err.println("menorValorId :"+menorValorId);
				if( menorValorId < recebeMenorValorId){
					recebeMenorValorId = menorValorId;
					
					System.err.println("recebeMenorValorId"
							+ "recebeMenorValorId:"+recebeMenorValorId);
					  verifica = this.verifcaId(j+1);
					    menorId = j+1+verifica;
				   System.err.println("j dentro do se:"+j);
					
				} 
				
		}
		  return menorId;
	}
	
	
	public String nomeTipoInsumo(int id) {
		String consulta ="select tp.nome from TipoInsumo tp where tp.codigo= :id";
		 TypedQuery<String> tp = manager.createQuery(consulta,String.class );
		 tp.setParameter("id", id);
		 return tp.getSingleResult();
	}

	
	public int verifcaId(int verificaId) {
		 String consulta="select SUM(i.pesoTotal) from Insumo i where i.tipoInsumo.codigo= :id";
		 TypedQuery<Double> tq= manager.createQuery(consulta,Double.class);
		 tq.setParameter("id", verificaId);
		 if(tq.getSingleResult()==null){
			 
			 return 1;
		 }
		
		 return 0;
		 
	  }
	
	
	public List<Insumo> pegarNome(String nome) {
		String consulta = "select i from Insumo i where i.tipoInsumo.nome like :nome";
		TypedQuery<Insumo> q = this.manager.createQuery(consulta,Insumo.class);
		q.setParameter("nome","%" + nome +"%");
		return q.getResultList();
	}
	*/
	
	
	
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
	
  public Insumo  ValoInsumo(int id){
		
	String consulta="select i from Insumo i where i.tipoInsumo.codigo= :id";
	TypedQuery<Insumo> tq = manager.createQuery(consulta, Insumo.class);
	tq.setParameter("id", id);
	 return tq.getSingleResult();
	     
	 } 
	 
  
	
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
		
	
	
	
	
	
	public Colaborador buscaLogin(String login) {
		System.err.println("----------"+login);
		String consulta ="select c from Colaborador c where c.login= :login";
		TypedQuery<Colaborador>  tq = this.manager.createQuery(consulta,Colaborador.class);
		tq.setParameter("login", login);
		return tq.getSingleResult();
	}
	
	

	
}
