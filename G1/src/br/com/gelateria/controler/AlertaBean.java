package br.com.gelateria.controler;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.dao.FornecedorDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Fornecedor;
import br.com.gelateria.persistence.AlertaDaoJpa;
import br.com.gelateria.persistence.FornecedorDaoJpa;


@ManagedBean
public class AlertaBean {

	private Alerta alerta;
	private List<Alerta> listaAlerta;
	private MessagensBean messagensBean;
    
	
	
	public AlertaBean(){
		this.alerta = new Alerta();
		this.messagensBean = new MessagensBean();
			 
	}
		
   public EntityManager getManager(){
	     FacesContext fc =  FacesContext.getCurrentInstance();
	     ExternalContext ec = fc.getExternalContext();
	     HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	     return (EntityManager) request.getAttribute("EntityManager");		    	
   }
	 
  public String inserir(){
	  EntityManager manager = this.getManager();
	  AlertaDao aDao = new AlertaDaoJpa(manager);
	  aDao.save(alerta);
	  this.messagensBean.info();
	  return "cadastro/alerta.xhtml";  
  } 
  
	public String preparaAlteracao() {
		EntityManager manager = this.getManager();
		AlertaDao aDao = new AlertaDaoJpa(manager);
		this.alerta = aDao.getById(Alerta.class, alerta.getCodigo());
		return "/cadastro/alerta.xhtml";
		}
	
   public String remove() {
		EntityManager manager = this.getManager();
		AlertaDao aDao = new AlertaDaoJpa(manager);
		aDao.remove(Alerta.class, alerta.getCodigo());
		this.listaAlerta = null;
		return "/lista/pesquisa-alerta.xhtml";
		
		}
	 

  public List<Alerta> getListaAlerta(){
	  if(this.listaAlerta == null){
		  EntityManager manager = this.getManager();
		  AlertaDao aDao = new AlertaDaoJpa(manager);
		 this.listaAlerta= aDao.getAll(Alerta.class);	

	  }
	  return listaAlerta;
  }
  
  
  public List<Alerta> buscarNome(){
	     EntityManager manager = this.getManager();
	    AlertaDao aDao =  new AlertaDaoJpa(manager);
		this.listaAlerta = aDao.pegarNome(alerta.getNome());   
	   return listaAlerta;
	   
}


public Alerta getAlerta() {
	return alerta;
}


public void setAlerta(Alerta alerta) {
	this.alerta = alerta;
	
}
  
  
	 
}
