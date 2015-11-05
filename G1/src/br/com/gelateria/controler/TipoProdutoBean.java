package br.com.gelateria.controler;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gelateria.dao.FornecedorDao;
import br.com.gelateria.dao.TipoProdutoDao;
import br.com.gelateria.model.Fornecedor;
import br.com.gelateria.model.TipoProduto;
import br.com.gelateria.persistence.FornecedorDaoJpa;
import br.com.gelateria.persistence.TipoProdutoDaoJpa;
import br.com.gelateria.util.Mensagens;

@ManagedBean
public class TipoProdutoBean {

	
	private TipoProduto tipoProduto;
	private List<TipoProduto> listaTipoProdutos;
	private Mensagens mensagens;
	
	
	
	public TipoProdutoBean(){
		this.tipoProduto = new TipoProduto();
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
		     TipoProdutoDao tpDao = new TipoProdutoDaoJpa(manager);
		     tpDao.save(tipoProduto);
		     this.mensagens.info();
		     this.tipoProduto = new TipoProduto();
	    }

	    
	    public void remove(){
	    	EntityManager manager = this.getManager();
		      TipoProdutoDao tpDao = new TipoProdutoDaoJpa(manager);
		     tpDao.remove(TipoProduto.class, tipoProduto.getCodigo());
		     this.mensagens.infoExclusao();
		    /* return "/lista/pesquisa_fornecedor.xhtml";*/
	    }
	    
	    public String preparaAlteracao(){
	    	 EntityManager manager = this.getManager();
	    	 TipoProdutoDao tpDao = new TipoProdutoDaoJpa(manager);
		     tpDao.getById(TipoProduto.class,tipoProduto.getCodigo());
		     return "/cadastro/tipo_produto.xhtml";
		     
	    }
	    
	    public List<TipoProduto> buscarNome(){
		     EntityManager manager = this.getManager();
		     TipoProdutoDao tpDao = new TipoProdutoDaoJpa(manager);
			this.listaTipoProdutos = tpDao.pegarNome(tipoProduto.getNome());  
			for (TipoProduto tipoProduto : listaTipoProdutos) {
				System.err.println(tipoProduto.getNome());
			}
		   return listaTipoProdutos;
		   
	  }
	    
		public List<TipoProduto> getListaTipoProdutos() {
			EntityManager manager = this.getManager();
			TipoProdutoDao  tpDao = new TipoProdutoDaoJpa(manager);
			this.listaTipoProdutos = tpDao.getAll(TipoProduto.class);
			return listaTipoProdutos;
		}


	

		public TipoProduto getTipoProduto() {
			return tipoProduto;
		}


		public void setTipoProduto(TipoProduto tipoProduto) {
			this.tipoProduto = tipoProduto;
		}
	    
	    
	    
}
