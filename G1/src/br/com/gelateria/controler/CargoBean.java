package br.com.gelateria.controler;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.dao.CargoDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Cargo;
import br.com.gelateria.persistence.AlertaDaoJpa;
import br.com.gelateria.persistence.CargoDaoJpa;
import br.com.gelateria.util.Mensagens;


@ManagedBean
public class CargoBean {
	
	private Cargo cargo;
	private List<Cargo> listaCargo;
	private Mensagens mensagens;

	
	public CargoBean(){
		this.cargo= new Cargo();
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
		     CargoDao cDao = new CargoDaoJpa(manager);
		     cDao.save(cargo);
		    this.mensagens.info();
		    	     
	    }

	    public List<Cargo> getListaCargo(){
	    	if (this.listaCargo == null) {	
	        EntityManager manager = this.getManager();
		    CargoDao cDao = new CargoDaoJpa(manager);
		    this.listaCargo=  cDao.getAll(Cargo.class);
	    	}
	    	return listaCargo;
	    }

	   public List<Cargo> buscarNome(){
		     EntityManager manager = this.getManager();
			    CargoDao cDao = new CargoDaoJpa(manager);
			this.listaCargo = cDao.pegarNome(cargo.getNome());   
		   return listaCargo;
		   
	   } 
	   
	   
	   public String remove() {
			EntityManager manager = this.getManager();
			 CargoDao cDao = new CargoDaoJpa(manager);
			cDao.remove(Cargo.class, cargo.getCodigo());
			this.listaCargo = null;
			return "/lista/pesquisa-cargo.xhtml";
			
	 }
	   
	   public String preparaAlteracao() {
			EntityManager manager = this.getManager();
			 CargoDao cDao = new CargoDaoJpa(manager);
			this.cargo = cDao.getById(Cargo.class, cargo.getCodigo());
			return "/cadastro/cargo.xhtml";
			}
	 
	   
		public Cargo getCargo() {
			return cargo;
		}


		public void setCargo(Cargo cargo) {
			this.cargo = cargo;
		}

		

	    
}
