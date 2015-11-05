package br.com.gelateria.controler;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.dao.BaseSorveteDao;
import br.com.gelateria.dao.ColaboradorDao;
import br.com.gelateria.dao.CompraDao;
import br.com.gelateria.dao.FabricacaoDao;
import br.com.gelateria.dao.InsumoDao;
import br.com.gelateria.dao.ProdutoDao;
import br.com.gelateria.dao.SaboresDao;
import br.com.gelateria.dao.TipoInsumoDao;
import br.com.gelateria.dao.TipoProdutoDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.BaseSorvete;
import br.com.gelateria.model.Colaborador;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Fabricacao;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.Produto;
import br.com.gelateria.model.Sabores;
import br.com.gelateria.model.TipoInsumo;
import br.com.gelateria.model.TipoProduto;
import br.com.gelateria.persistence.AlertaDaoJpa;
import br.com.gelateria.persistence.BaseSorveteDaoJpa;
import br.com.gelateria.persistence.ColaboradorDaoJpa;
import br.com.gelateria.persistence.CompraDaoJpa;
import br.com.gelateria.persistence.FabricacaoDaoJpa;
import br.com.gelateria.persistence.InsumoDaoJpa;
import br.com.gelateria.persistence.ProdutoDaoJpa;
import br.com.gelateria.persistence.SaboresDaoJpa;
import br.com.gelateria.persistence.TipoInsumoDaoJpa;
import br.com.gelateria.persistence.TipoProdutoDaoJpa;

@ManagedBean
public class ProdutoBean {
	
	private Fabricacao fabricacao;
	private Colaborador colaborador;
	private int colaboradorId;
	private List<TipoProduto> listaTipoProdutos;
	private int tipoProdutoId;
	private int qta;
	private List<Colaborador> listaColaborador;
	private List<Alerta> listaAleta;
	private Produto produto;
	private Date data;
	private List listaproduto;
	
	
	public ProdutoBean(){
		this.colaborador = new Colaborador();
		this.fabricacao = new Fabricacao();
		this.produto = new Produto();
		
		
	}
	
	public EntityManager getManager(){
	    	FacesContext fc =  FacesContext.getCurrentInstance();
	    	ExternalContext ec = fc.getExternalContext();
	    	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	    	return (EntityManager) request.getAttribute("EntityManager");		    	
	    }
	    
	    
	  public void inserir(){
		     EntityManager manager = this.getManager();
		     ColaboradorDao clbDao = new ColaboradorDaoJpa(manager);
             ProdutoDao pDao = new ProdutoDaoJpa(manager);
             
		     if(this.colaboradorId != 0){

		    	Colaborador c = clbDao.getById(Colaborador.class, colaboradorId);
		    	this.produto.setColaborador(c);
		    	
		    	
		     }
		     this.produto.setFabricacao(getUltimoId());
		     this.produto.setDataProduto(this.converterCalendarEmDate());
		     pDao.save(produto);   
		     
		    
		    /*
		     depois de fazer a inserção de fabricação 
		     fazer em estoqueinsumo através
		     do tipo do produto*/
	    }


	public List<TipoProduto> getListaTipoProdutos() {
		EntityManager manager = this.getManager();
		TipoProdutoDao tpDao = new TipoProdutoDaoJpa(manager);
		this.listaTipoProdutos = tpDao.getAll(TipoProduto.class);
		return listaTipoProdutos;
	}
	
	  public List<Colaborador> getListaColaborador(){
	    	if (this.listaColaborador == null) {	
	    		  EntityManager manager = this.getManager();
	 		     ColaboradorDao cDao = new ColaboradorDaoJpa(manager);
		    this.listaColaborador=  cDao.getAll(Colaborador.class);
	    	}
	    	return listaColaborador;
	    }

	
	 public Fabricacao getUltimoId(){
	    	EntityManager manager = this.getManager();
	    	FabricacaoDao fDao = new FabricacaoDaoJpa(manager);
	    	 this.fabricacao = fDao.pegarUltimoId();
	    	return fabricacao;
	    }
	
	 public List<Alerta> getListaAlerta(){
		  EntityManager manager = this.getManager();
		     AlertaDao aDao = new AlertaDaoJpa(manager);
		    this.listaAleta= aDao.getAll(Alerta.class);
	    return listaAleta;	     
	}
	 
	 private Calendar converterCalendarEmDate(){
		 Date data = this.getData();
		 Calendar calendar = new GregorianCalendar();
			calendar.setTime(data);
			 return calendar;
		
	 }
	 
	
	 
	 private Date converterEmDate(Calendar calendar){	 
		 Calendar calendario = calendar;
		 Date data = null;
		 data= calendario.getTime();
			 return data;
		
	 }
	 
	 public Date novaData(){	 
		 Calendar calendario = this.produto.getDataProduto();	 
			 Date data = null;
			 data= calendario.getTime();
				 return data;
			
		 }

	
	 
	 public String preparaAlteracao() {
			EntityManager manager = this.getManager();
			ProdutoDao aDao = new ProdutoDaoJpa(manager);
			this.produto = aDao.getById(Produto.class, produto.getCodigo());
			this.colaboradorId = this.produto.getColaborador().getCondigo();
			this.data = this.converterEmDate(this.produto.getDataProduto());
			return "/cadastro/produto.xhtml";
			}
		
	  
			
		   public String remove() {
				EntityManager manager = this.getManager();
				ProdutoDao aDao = new ProdutoDaoJpa(manager);
				aDao.remove(Produto.class, produto.getCodigo());
				
				return "/lista/pesquisa-produto.xhtml";
				
				}
			 

		  public List<Produto> getListaProduto(){
			  if(this.listaproduto == null){
				  EntityManager manager = this.getManager();
				  ProdutoDao aDao = new ProdutoDaoJpa(manager);
				 this.listaproduto= aDao.getAll(Produto.class);	

			  }
			  return listaproduto;
		  }
		  
		  
		  public List<Produto> buscarQta(){
			     EntityManager manager = this.getManager();
			    ProdutoDao aDao =  new ProdutoDaoJpa(manager);
				this.listaproduto = aDao.pegarQta(produto.getQuantidade());   
			   return listaproduto;
			   
		}

			
			
			
	  
	  



	public void setListaTipoProdutos(List<TipoProduto> listaTipoProdutos) {
		this.listaTipoProdutos = listaTipoProdutos;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}


	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}


	
	  
	 public Fabricacao getFabricacao() {
			return fabricacao;
		}


		public void setFabricacao(Fabricacao fabricacao) {
			this.fabricacao = fabricacao;
		}

	

		public int getTipoProdutoId() {
			return tipoProdutoId;
		}


		public void setTipoProdutoId(int tipoProdutoId) {
			this.tipoProdutoId = tipoProdutoId;
		}

		public int getQta() {
			return qta;
		}

		public void setQta(int qta) {
			this.qta = qta;
		}

		public int getColaboradorId() {
			return colaboradorId;
		}

		public void setColaboradorId(int colaboradorId) {
			this.colaboradorId = colaboradorId;
		}

		public Produto getProduto() {
			return produto;
		}

		public void setProduto(Produto produto) {
			this.produto = produto;
		}

		public Date getData() {
			return data;
		}

		public void setData(Date data) {
			this.data = data;
		}
		
		
		
	  
	  
	  
	   

	   

}
