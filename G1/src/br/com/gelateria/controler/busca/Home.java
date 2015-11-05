package br.com.gelateria.controler.busca;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.dao.BaseSorveteDao;
import br.com.gelateria.dao.CompraDao;
import br.com.gelateria.dao.FabricacaoDao;
import br.com.gelateria.dao.InsumoDao;
import br.com.gelateria.dao.ProdutoDao;
import br.com.gelateria.dao.TipoInsumoDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.BaseSorvete;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Fabricacao;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.Produto;
import br.com.gelateria.model.TipoInsumo;
import br.com.gelateria.persistence.AlertaDaoJpa;
import br.com.gelateria.persistence.BaseSorveteDaoJpa;
import br.com.gelateria.persistence.CompraDaoJpa;
import br.com.gelateria.persistence.FabricacaoDaoJpa;
import br.com.gelateria.persistence.InsumoDaoJpa;
import br.com.gelateria.persistence.ProdutoDaoJpa;
import br.com.gelateria.persistence.TipoInsumoDaoJpa;

@ManagedBean
public class Home {
	
	private List listaFabricacao;
	private Insumo insumo;
	private List listaInsumo;
	private Fabricacao fabricacao;
	private BaseSorvete baseSorvete;
	private List listaProduto;
	private Compra compra;
	private Alerta alerta;
	private Produto produto;
	private List listaTipoInsumo;
	private TipoInsumo tipoInsumo;
	
	
	/**
	 * 
	 */
	public Home() {
		this.insumo = new Insumo();
		this.fabricacao = new Fabricacao();
		this.baseSorvete = new BaseSorvete();
		this.compra = new Compra();
		this.alerta = new Alerta();
		this.produto = new Produto();

	}

	public Fabricacao getFabricacao() {
		return fabricacao;
	}

	public void setFabricacao(Fabricacao fabricacao) {
		this.fabricacao = fabricacao;
	}

	public EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

	public Insumo getMaiorQtaInsumo(){
		 EntityManager manager = this.getManager();
   	 InsumoDao iDao = new InsumoDaoJpa(manager);
   	 int id = iDao.maiorValorInsumo();
   	 this.insumo= iDao.ValoInsumo(id);
   	 return insumo;
   	 
	}
	
	public Insumo getMenorQtaInsumo(){
		 EntityManager manager = this.getManager();
  	 InsumoDao iDao = new InsumoDaoJpa(manager);
  	 int id = iDao.menorValorInsumo();
  	 this.insumo= iDao.ValoInsumo(id);
  	 return insumo;
  	 
	}
	
	/*
	 * public double getMaiorValorInsumo(){ double maiorEstoque; EntityManager
	 * manager = this.getManager(); InsumoDao iDao = new InsumoDaoJpa(manager);
	 * maiorEstoque = iDao.maiorValor(); return maiorEstoque;
	 * 
	 * }
	 */

	/*
	 * public String getNomeMaiorEstoque(){ String nome; int id; EntityManager
	 * manager = this.getManager(); InsumoDao iDao = new InsumoDaoJpa(manager);
	 * id = iDao.maiorValorId(); nome = iDao.nomeTipoInsumo(id);
	 * System.out.println(nome); return nome; }
	 */
	/*
	 * public double getMenorValorInsumo(){ double menorEstoque; EntityManager
	 * manager = this.getManager(); InsumoDao iDao = new InsumoDaoJpa(manager);
	 * menorEstoque = iDao.menorValor(); return menorEstoque;
	 * 
	 * }
	 * 
	 * public String getNomeMenorEstoque(){ String nome; int id; EntityManager
	 * manager = this.getManager(); InsumoDao iDao = new InsumoDaoJpa(manager);
	 * id = iDao.menorValorId(); nome = iDao.nomeTipoInsumo(id); return nome; }
	 */

	public List<Fabricacao> getListUltimasFabricacao() {
		EntityManager manager = this.getManager();
		FabricacaoDao fDao = new FabricacaoDaoJpa(manager);
		this.listaFabricacao = fDao.buscarTresUltimoFabricacao();
		return listaFabricacao;
	}

	public List<Produto> getTresUltimoProduto() {
		EntityManager manager = this.getManager();
		ProdutoDao pDao = new ProdutoDaoJpa(manager);
		this.listaProduto = pDao.tresUltimasProducao();
		return listaProduto;

	}

	public Compra getPegarUltimaCompra() {
		EntityManager manager = this.getManager();
		CompraDao cDao = new CompraDaoJpa(manager);
		this.compra = cDao.pegarUltimoIdCompra();
		return compra;

	}

	public List<Insumo> getultimoAlertaInsumo() {
		EntityManager manager = this.getManager();
		AlertaDao aDao = new AlertaDaoJpa(manager);
		InsumoDao iDao = new InsumoDaoJpa(manager);
		int id = iDao.ultimoIdInsumo();
		this.listaInsumo = aDao.ultimoAlertaInsumo(id);
		return listaInsumo;

	}

	public Produto getUltimaProducao() {
		EntityManager manager = this.getManager();
		ProdutoDao pDao = new ProdutoDaoJpa(manager);
		this.produto = pDao.ultimaProducao();
		return produto;
	}

	public Fabricacao getUltimaFabricacao() {
		EntityManager manager = this.getManager();
		FabricacaoDao fDao = new FabricacaoDaoJpa(manager);
		this.fabricacao = fDao.pegarUltimoId();
		return fabricacao;
	}

	public double getVolumeTotalFabricacaoSorvete() {
		EntityManager manager = this.getManager();
		FabricacaoDao fDao = new FabricacaoDaoJpa(manager);
		double fabricacaoSorvete = fDao.fabricacaoTotalSorvete("Sorvete");
		return fabricacaoSorvete;
	}

	public Insumo getUltimoId() {
		EntityManager manager = this.getManager();
		InsumoDao iDao = new InsumoDaoJpa(manager);
		this.insumo = iDao.ultimoId();
		return insumo;
	}

	public double getMaiorValorMaior() {
		double mMValor = 0;
		int id = 0;
		EntityManager manager = this.getManager();
		TipoInsumoDao tiDao = new TipoInsumoDaoJpa(manager);
		this.listaTipoInsumo = tiDao.pegarIdTipoÎnsumo();
		for (int i = 0; i < this.listaTipoInsumo.size(); i++) {
			if (tiDao.pegarQtdDeUmInsumo().get(i) != null) {
				if (mMValor < tiDao.pegarQtdDeUmInsumo().get(i)) {
					mMValor = tiDao.pegarQtdDeUmInsumo().get(i);
					id = i + 1;
				}
			}
		}
		this.tipoInsumo = tiDao.getById(TipoInsumo.class, id);
		this.getIdCorrespondenteMaior(tipoInsumo);
		return mMValor;
	}

	public double getMenorValorMenor() {
		double mMValor = this.getMaiorValorMaior();
		int id = 0;
		EntityManager manager = this.getManager();
		TipoInsumoDao tiDao = new TipoInsumoDaoJpa(manager);
		this.listaTipoInsumo = tiDao.pegarIdTipoÎnsumo();
		for (int i = 0; i < this.listaTipoInsumo.size(); i++) {
			if (tiDao.pegarQtdDeUmInsumo().get(i) != null) {
				if (mMValor > tiDao.pegarQtdDeUmInsumo().get(i)) {
					mMValor = tiDao.pegarQtdDeUmInsumo().get(i);
					id = i + 1;
				}
			}
		}
		this.tipoInsumo = tiDao.getById(TipoInsumo.class, id);
		this.getIdCorrespondenteMenor(tipoInsumo);
		return mMValor;
	}

	public TipoInsumo getIdCorrespondenteMenor(TipoInsumo tipoInsumo) {

		return this.tipoInsumo = tipoInsumo;

	}

	public TipoInsumo getIdCorrespondenteMaior(TipoInsumo tipoInsumo) {

		return this.tipoInsumo = tipoInsumo;

	}

	public List getListaFabricacao() {
		return listaFabricacao;
	}

	public void setListaFabricacao(List listaFabricacao) {
		this.listaFabricacao = listaFabricacao;
	}

	public List getListaTipoInsumo() {
		return listaTipoInsumo;
	}

	public void setListaTipoInsumo(List listaTipoInsumo) {
		this.listaTipoInsumo = listaTipoInsumo;
	}

	public TipoInsumo getTipoInsumo() {
		return tipoInsumo;
	}

	public void setTipoInsumo(TipoInsumo tipoInsumo) {
		this.tipoInsumo = tipoInsumo;
	}
		
		
		    
		
  
	
  
	
}
