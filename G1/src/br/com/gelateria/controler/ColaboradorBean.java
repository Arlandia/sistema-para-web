package br.com.gelateria.controler;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.dao.CargoDao;
import br.com.gelateria.dao.ColaboradorDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Cargo;
import br.com.gelateria.model.Colaborador;
import br.com.gelateria.persistence.AlertaDaoJpa;
import br.com.gelateria.persistence.CargoDaoJpa;
import br.com.gelateria.persistence.ColaboradorDaoJpa;
import br.com.gelateria.util.Mensagens;

@ManagedBean
public class ColaboradorBean {
	
	private Colaborador colaborador;
	private List<Colaborador> listaColaborador;
	private Cargo cargo;
	private int cargoId;
	private Mensagens mensagens;
	

	
	public ColaboradorBean(){
		this.colaborador= new Colaborador();
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
		     ColaboradorDao cDao = new ColaboradorDaoJpa(manager);
		     CargoDao cgDao = new CargoDaoJpa(manager);
		     if(cargoId != 0){
		    	Cargo cg= cgDao.getById(Cargo.class, cargoId);
		    	this.colaborador.setCargo(cg);
		    	this.cargo = cg;
		     }
		     cDao.save(colaborador);
		     this.mensagens.info();
		 
		    
		    	     
	    }
	    
	    public List<Colaborador> getListaColaborador(){
	    	if (this.listaColaborador == null) {	
	    		  EntityManager manager = this.getManager();
	 		     ColaboradorDao cDao = new ColaboradorDaoJpa(manager);
		    this.listaColaborador=  cDao.getAll(Colaborador.class);
	    	}
	    	return listaColaborador;
	    }
	    
	    
	    public String preparaAlteracao() {
			EntityManager manager = this.getManager();   
			ColaboradorDao cDao = new ColaboradorDaoJpa(manager);
			CargoDao cgDao= new CargoDaoJpa(manager);
			this.colaborador = cDao.getById(Colaborador.class, colaborador.getCondigo());
			this.cargoId = this.colaborador.getCargo().getCodigo();
			
			return "/cadastro/colaborador.xhtml";
			}
		
	   public String remove() {
			EntityManager manager = this.getManager();
		    ColaboradorDao cDao = new ColaboradorDaoJpa(manager);
			cDao.remove(Colaborador.class, colaborador.getCondigo());
			return "/lista/pesquisa-colaborador.xhtml";
			
			}
		 
	   public List<Colaborador> buscarNome(){
		     EntityManager manager = this.getManager();
		     ColaboradorDao cDao = new ColaboradorDaoJpa(manager);
			this.listaColaborador = cDao.pegarNome(colaborador.getNome());   
		   return listaColaborador;
		   
	}  
	    
	   @Transactional
	   public Colaborador buscarLogin(String login){
		   EntityManager manager = this.getManager();
		    ColaboradorDao cDao = new ColaboradorDaoJpa(manager);
		    this.colaborador = cDao.buscaLogin(login);
		    return colaborador;
	   } 
	  

		public Colaborador getColaborador() {
			return colaborador;
		}

		public void setColaborador(Colaborador colaborador) {
			this.colaborador = colaborador;
		}

		public Cargo getCargo() {
			return cargo;
		}

		public void setCargo(Cargo cargo) {
			this.cargo = cargo;
		}

		public int getCargoId() {
			return cargoId;
		}

		public void setCargoId(int cargoId) {
			this.cargoId = cargoId;
		}
	    
	
      

}
