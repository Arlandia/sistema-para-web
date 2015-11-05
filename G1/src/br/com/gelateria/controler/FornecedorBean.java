package br.com.gelateria.controler;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gelateria.controler.componente.limparComponente;
import br.com.gelateria.dao.CargoDao;
import br.com.gelateria.dao.CompraDao;
import br.com.gelateria.dao.FornecedorDao;
import br.com.gelateria.model.Cargo;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Fornecedor;
import br.com.gelateria.persistence.CargoDaoJpa;
import br.com.gelateria.persistence.CompraDaoJpa;
import br.com.gelateria.persistence.FornecedorDaoJpa;
import br.com.gelateria.util.Mensagens;


@ManagedBean
public class FornecedorBean {

	private Fornecedor fornecedor;
	private List<Fornecedor> listaFornecedor;
	private Mensagens mensagens;
	private int fornecedorId;
	private MessagensBean messagensBean;
	
	
	
    public FornecedorBean(){
    	this.fornecedor= new Fornecedor();
    	this.mensagens = new Mensagens();
    	this.messagensBean = new MessagensBean();
    }	
    
    public EntityManager getManager(){
    	FacesContext fc =  FacesContext.getCurrentInstance();
    	ExternalContext ec = fc.getExternalContext();
    	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
    	return (EntityManager) request.getAttribute("EntityManager");		    	
    }
    
    
    public void inserir(){
	     EntityManager manager = this.getManager();
	     FornecedorDao fncDao = new FornecedorDaoJpa(manager);
	     fncDao.save(fornecedor);
	     this.mensagens.info();

	     FacesContext facesContext = FacesContext.getCurrentInstance();
	 	UIViewRoot uiViewRoot = facesContext.getViewRoot();
	    limparComponente.cleanSubmittedValues(uiViewRoot.findComponent("form:painel"));
	     /*return "/cadastro/fornecedor.xhtml";*/
    }
    
  
    
    public void remove(){
    	EntityManager manager = this.getManager();
	     FornecedorDao fncDao = new FornecedorDaoJpa(manager);
	     fncDao.remove(Fornecedor.class, fornecedor.getCodigo());
	     this.mensagens.infoExclusao();
	    /* return "/lista/pesquisa_fornecedor.xhtml";*/
    }
    
    public String preparaAlteracao(){
    	 EntityManager manager = this.getManager();
	     FornecedorDao fncDao = new FornecedorDaoJpa(manager);
	     fncDao.getById(Fornecedor.class,fornecedor.getCodigo());
	     return "/cadastro/fornecedor.xhtml";
	     
    }
    
    public List<Fornecedor> buscarNome(){
	     EntityManager manager = this.getManager();
	     FornecedorDao fncDao = new FornecedorDaoJpa(manager); 
		this.listaFornecedor = fncDao.pegarNome(fornecedor.getNome());   
	   return listaFornecedor;
	   
  }
    
    
    public List<Fornecedor> getListaFornecedor(){
    	if (this.listaFornecedor == null) {	
    		  EntityManager manager = this.getManager();
 		     FornecedorDao  fDao = new FornecedorDaoJpa(manager);
	    this.listaFornecedor=  fDao.getAll(Fornecedor.class);
    	}
    	return listaFornecedor;
    }

    

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(int fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

   
    
	
}
