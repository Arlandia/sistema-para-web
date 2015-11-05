package br.com.gelateria.controler;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.dao.ColaboradorDao;
import br.com.gelateria.dao.CompraDao;
import br.com.gelateria.dao.FornecedorDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Colaborador;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Fornecedor;
import br.com.gelateria.persistence.AlertaDaoJpa;
import br.com.gelateria.persistence.ColaboradorDaoJpa;
import br.com.gelateria.persistence.CompraDaoJpa;
import br.com.gelateria.persistence.FornecedorDaoJpa;
import br.com.gelateria.util.Mensagens;

@ManagedBean
public class CompraBean {
	
	private Compra compra;
	private List<Compra> listaCompra;

	private int colaboradorId;
	private Colaborador colaborador;
	private int fornecedorId;
	private Fornecedor fornecedor;
	private Alerta alerta;
	private List<Alerta> listaAlerta;
	private Mensagens mensagens;
	private List<Fornecedor> listaFornecedor;

	



	public CompraBean(){
		this.compra = new Compra();
		this.fornecedor = new Fornecedor();
		this.colaborador = new Colaborador();
		this.alerta = new Alerta();
		this.mensagens = new Mensagens();
		
	}
	
	
	 public EntityManager getManager(){
	    	FacesContext fc =  FacesContext.getCurrentInstance();
	    	ExternalContext ec = fc.getExternalContext();
	    	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	    	return (EntityManager) request.getAttribute("EntityManager");		    	
	    }
	    
	    
	  public void inserir(){
		     EntityManager manager = this.getManager();
		     CompraDao cDao = new CompraDaoJpa(manager);
		     ColaboradorDao clbDao = new ColaboradorDaoJpa(manager);
		     FornecedorDao fndDao= new FornecedorDaoJpa(manager);
		     if(this.colaboradorId != 0){
		    	Colaborador clb = clbDao.getById(Colaborador.class, colaboradorId);
		    	this.compra.setColaborador(clb);
		    	Fornecedor fnd = fndDao.getById(Fornecedor.class, fornecedorId);
		        this.compra.setFornecedor(fnd);
	               
		     }
		     
		     this.pegarAlerta();
		     cDao.save(compra);
		     /*alerta.getListaCompras().add(compra);*/
		     this.mensagens.info();
		     this.redireciona();
		    		   
	    }
	  
	  private String redireciona(){
		  return"/faces/cadastro/insumo.xhtml";
	  }
	  
	   public void pegarAlerta(){
		   EntityManager manager = this.getManager();
		     AlertaDao cDao = new AlertaDaoJpa(manager);
		      if(this.compra.getConfirmarChegada().equals("Sim")){
			    this.listaAlerta =cDao.pegarAlerta("S001");  
			    this.compra.setListaAlertaCompras(listaAlerta);
		   }else{
			    this.listaAlerta =cDao.pegarAlerta("C001");
			    this.compra.setListaAlertaCompras(listaAlerta);		   
		   }
	   }

	    public List<Compra> getListaCompra(){
	    	if (this.listaCompra == null) {	
	       	  EntityManager manager = this.getManager();
	 		  CompraDao cDao = new CompraDaoJpa(manager);
		    this.listaCompra=  cDao.getAll(Compra.class);
	    	}
	   
	    	return listaCompra;
	    }
	    
	    public String preparaAlteracao(){
	    	 EntityManager manager = this.getManager();
		     CompraDao cDao = new CompraDaoJpa(manager);
		     this.compra=cDao.getById(Compra.class,compra.getCodigo());
		     this.fornecedorId = this.compra.getFornecedor().getCodigo();
		     this.colaboradorId = this.compra.getColaborador().getCondigo();
		     
		     return "/cadastro/compra.xhtml";
		     
	    }
	    
	    public List<Compra> getBuscaQta(){
		     EntityManager manager = this.getManager();
		     CompraDao cDao = new CompraDaoJpa(manager);
			this.listaCompra= cDao.pegarQtaItens(compra.getItens());   
		   return listaCompra;
		   
	  }
	    public List<Compra> buscarNumeroCupom(){
		     EntityManager manager = this.getManager();
		     CompraDao cDao = new CompraDaoJpa(manager);
			 this.listaCompra= cDao.buscarNumeroCupom(compra.getNumeroCupom());   
			 return listaCompra;
		   
	    }
	    
	    public void remove(){
	    	EntityManager manager = this.getManager();
		     CompraDao cDao = new CompraDaoJpa(manager);
		     cDao.getById(Compra.class,compra.getCodigo());
		     this.mensagens.infoExclusao();
		    /* return "/lista/pesquisa_fornecedor.xhtml";*/
	    }
	    
	  
	    
  	public Compra getCompra() {
			return compra;
	
	}


		public void setCompra(Compra compra) {
			
			this.compra = compra;
			
			
		}


		public int getColaboradorId() {
			return colaboradorId;
		}


		public void setColaboradorId(int colaboradorId) {
			this.colaboradorId = colaboradorId;
		}


		public Colaborador getColaborador() {
			return colaborador;
		}


		public void setColaborador(Colaborador colaborador) {
			this.colaborador = colaborador;
		}


		public int getFornecedorId() {
			return fornecedorId;
		}


		public void setFornecedorId(int fornecedorId) {
			this.fornecedorId = fornecedorId;
		}


		public Fornecedor getFornecedor() {
			return fornecedor;
		}


		public void setFornecedor(Fornecedor fornecedor) {
			this.fornecedor = fornecedor;
		}





	
	    
		
		
		   /* pegando objeto
	    public List<Compra> getTeste(){
	    	 EntityManager manager = this.getManager();
	    	   CompraDao cDao = new CompraDaoJpa(manager);
	    	  this.compra.setCodigo(1);
	    	 this.listaCompra=  cDao.pegarObjeto(compra);
	    	return listaCompra;
	    }*/
		

		}
		
		
	    


