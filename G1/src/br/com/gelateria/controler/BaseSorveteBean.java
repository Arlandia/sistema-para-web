package br.com.gelateria.controler;


import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gelateria.dao.BaseSorveteDao;
import br.com.gelateria.model.BaseSorvete;
import br.com.gelateria.persistence.BaseSorveteDaoJpa;
import br.com.gelateria.util.Mensagens;

@ManagedBean
public class BaseSorveteBean {

	
	private BaseSorvete baseSorvete;
	private Mensagens mensagens;
	
	
	
	public BaseSorveteBean(){
		this.mensagens = new Mensagens();
		this.baseSorvete = new BaseSorvete();
	}
	
	
	 public EntityManager getManager(){
	    	FacesContext fc =  FacesContext.getCurrentInstance();
	    	ExternalContext ec = fc.getExternalContext();
	    	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	    	return (EntityManager) request.getAttribute("EntityManager");		    	
	    }
	    
	    
	    public void inserir(){
		     EntityManager manager = this.getManager();
		     BaseSorveteDao bsDao = new BaseSorveteDaoJpa(manager);
		     this.baseSorvete.setVolumeUnitario(this.somarVolumeUnitario());
		     bsDao.save(baseSorvete);
		     
		   
	    }

	    
	    public BaseSorvete getUltimoDados(){
	    	EntityManager manager = this.getManager();
	    	BaseSorveteDao bsDao = new BaseSorveteDaoJpa(manager);
	    	BaseSorvete bs = bsDao.selecionarUltimo();
	    	return bs;
	    }
	    
	    
	    public double somarVolumeUnitario(){
	    	 double ligaNeutra = this.baseSorvete.getLigaNeutra();
	    	 double sabor = this.baseSorvete.getSabor();
	         double emulsao = this.baseSorvete.getEmulsao();
	    	 double leiteCondesado = this.baseSorvete.getLeiteCondesado();
	    	 double acucar = this.baseSorvete.getAcucar();
	    	 double leite = this.baseSorvete.getLeite();
	    	 double  Chocolate = this.baseSorvete.getChocolate();
	    	 double soma= ligaNeutra+sabor+emulsao+leiteCondesado+acucar+leite+Chocolate;
	    	return soma;
	    }

		public BaseSorvete getBaseSorvete() {
			return baseSorvete;
		}


		public void setBaseSorvete(BaseSorvete baseSorvete) {
			this.baseSorvete = baseSorvete;
		}


		
	    
	    
	    


		
	    
	    
	    
}
