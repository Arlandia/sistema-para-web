package br.com.gelateria.controler;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gelateria.dao.FornecedorDao;
import br.com.gelateria.dao.TipoInsumoDao;
import br.com.gelateria.model.Fornecedor;
import br.com.gelateria.model.TipoInsumo;
import br.com.gelateria.model.TipoProduto;
import br.com.gelateria.persistence.FornecedorDaoJpa;
import br.com.gelateria.persistence.TipoInsumoDaoJpa;
import br.com.gelateria.util.Mensagens;

@ManagedBean
public class TipoInsumoBean {

	private TipoInsumo tipoInsumo;
	private List<TipoInsumo> listaTipoInsumo;
	private TipoProduto tipoProduto;
	Mensagens mensagens;
	
	
	public TipoInsumoBean(){
		this.tipoInsumo= new TipoInsumo();
	}
	
	
	public void inserir(){
		EntityManager manager = this.getManager();
		TipoInsumoDao tiDao =  new TipoInsumoDaoJpa(manager);		
		tiDao.save(tipoInsumo);
		
	}
	
	public EntityManager getManager(){
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request =(HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

	
	public List<TipoInsumo> getListaTipoInsumo(){
		EntityManager manager = this.getManager();
		TipoInsumoDao tiDao =  new TipoInsumoDaoJpa(manager);
		this.listaTipoInsumo=tiDao.getAll(TipoInsumo.class);
		return listaTipoInsumo;
	}


	
	
    public void remove(){
    	EntityManager manager = this.getManager();
    	TipoInsumoDao tiDao =  new TipoInsumoDaoJpa(manager);
	     tiDao.remove(TipoInsumo.class, tipoInsumo.getCodigo());
	     this.mensagens.infoExclusao();
	    /* return "/lista/pesquisa_fornecedor.xhtml";*/
    }
    
    public String preparaAlteracao(){
    	 EntityManager manager = this.getManager();
    	 TipoInsumoDao tiDao =  new TipoInsumoDaoJpa(manager);
	     tiDao.getById(TipoInsumo.class, tipoInsumo.getCodigo());
	     return "/cadastro/tipo_insumo.xhtml";
	     
    }
    
    public List<TipoInsumo> buscarNome(){
	     EntityManager manager = this.getManager();
	     TipoInsumoDao tiDao =  new TipoInsumoDaoJpa(manager);
		this.listaTipoInsumo = tiDao.pegarNome(tipoInsumo.getNome());   
	   return listaTipoInsumo;
	   
  }
    


	public TipoInsumo getTipoInsumo() {
		return tipoInsumo;
	}
    
	public void setTipoInsumo(TipoInsumo tipoInsumo) {
		this.tipoInsumo = tipoInsumo;
	}


	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}


	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	
	
	


	
}
