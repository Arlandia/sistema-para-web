package br.com.gelateria.controler;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.message.Message;

import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.dao.SaboresDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Sabores;
import br.com.gelateria.persistence.AlertaDaoJpa;
import br.com.gelateria.persistence.SaboresDaoJpa;
import br.com.gelateria.util.Mensagens;

@ManagedBean
public class SaboresBean {
	
	private Sabores sabores;
	private List<Sabores> listaSabores;
	private Mensagens mensagens;
	private int saboresId;
	
	
	public SaboresBean(){
		this.sabores= new Sabores();
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
		SaboresDao sDao = new SaboresDaoJpa(manager);
		sDao.save(sabores);
		this.mensagens.info();
	}
	
	

	
	public List<Sabores> getListaSabores() {
		EntityManager manager = this.getManager();
		SaboresDao sDao = new SaboresDaoJpa(manager);
		this.listaSabores =sDao.getAll(Sabores.class);	
		return listaSabores;
	}

	
	public String preparaAlteracao() {
		EntityManager manager = this.getManager();
		SaboresDao sDao = new SaboresDaoJpa(manager);
		this.sabores = sDao.getById(Sabores.class, sabores.getCodigo());
		return "/cadastro/sabores.xhtml";
		}
	
   public String remove() {
		EntityManager manager = this.getManager();
		SaboresDao sDao = new SaboresDaoJpa(manager);
		sDao.remove(Sabores.class, sabores.getCodigo());
	
		return "/lista/pesquisa-sabores.xhtml";
		
		}
	 

  
  
  public List<Sabores> buscarNome(){
	     EntityManager manager = this.getManager();
	 	SaboresDao sDao = new SaboresDaoJpa(manager);
		this.listaSabores =sDao.buscarNome( sabores.getNome());  
	   return listaSabores;
	   
}
	
	
	public int getSaboresId() {
		return saboresId;
	}

	public void setSaboresId(int saboresId) {
		this.saboresId = saboresId;
	}

	public void setListaSabores(List<Sabores> listaSabores) {
		this.listaSabores = listaSabores;
	}

	public Sabores getSabores() {
		return sabores;
	}

	public void setSabores(Sabores sabores) {
		this.sabores = sabores;
	}
	
	
}
