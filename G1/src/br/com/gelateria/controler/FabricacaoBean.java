package br.com.gelateria.controler;

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
import br.com.gelateria.dao.SaboresDao;
import br.com.gelateria.dao.TipoInsumoDao;
import br.com.gelateria.dao.TipoProdutoDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.BaseSorvete;
import br.com.gelateria.model.Colaborador;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Fabricacao;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.Sabores;
import br.com.gelateria.model.TipoInsumo;
import br.com.gelateria.model.TipoProduto;
import br.com.gelateria.persistence.AlertaDaoJpa;
import br.com.gelateria.persistence.BaseSorveteDaoJpa;
import br.com.gelateria.persistence.ColaboradorDaoJpa;
import br.com.gelateria.persistence.CompraDaoJpa;
import br.com.gelateria.persistence.FabricacaoDaoJpa;
import br.com.gelateria.persistence.InsumoDaoJpa;
import br.com.gelateria.persistence.SaboresDaoJpa;
import br.com.gelateria.persistence.TipoInsumoDaoJpa;
import br.com.gelateria.persistence.TipoProdutoDaoJpa;

@ManagedBean
public class FabricacaoBean {
	
	private Fabricacao fabricacao;
	private Colaborador colaborador;
	private int colaboradorId;
	private Sabores sabores;
	private int saboresId;
	private List<Sabores> listaSabores;
	private List<TipoProduto> listaTipoProdutos;
	private int tipoProdutoId;
	private int qta;
	private List<Colaborador> listaColaborador;
	private List<Alerta> listaAleta;
	private BaseSorvete baseSorvete;
	private TipoProduto tipoProduto;
	private List<Fabricacao> listaFabricacao;
	
	public FabricacaoBean() {
		this.fabricacao = new Fabricacao();
		this.baseSorvete = new BaseSorvete();
		this.tipoProduto = new TipoProduto();
	}

	public EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

	public void inserir() {
		EntityManager manager = this.getManager();
		ColaboradorDao clbDao = new ColaboradorDaoJpa(manager);
		TipoProdutoDao tpDao = new TipoProdutoDaoJpa(manager);
		SaboresDao sbDao = new SaboresDaoJpa(manager);
		FabricacaoDao fDao = new FabricacaoDaoJpa(manager);
		if (this.tipoProdutoId != 0) {

			TipoProduto tp = tpDao.getById(TipoProduto.class, tipoProdutoId);
			this.fabricacao.setTipoProduto(tp);

			Colaborador c = clbDao.getById(Colaborador.class, colaboradorId);
			this.fabricacao.setColaborador(c);
			
			Sabores s = sbDao.getById(Sabores.class, saboresId);
			this.fabricacao.setSabores(s);
		}
        this.fabricacao.setVolumeTotal(getVolumeTotal());
		fDao.save(fabricacao);

		/*
		 * depois de fazer a inserção de fabricação fazer em estoqueinsumo
		 * através do tipo do produto
		 */
	}

	public String pegaTipoProduto() {
		EntityManager manager = this.getManager();
		int id = this.fabricacao.getTipoProduto().getCodigo();
		TipoProdutoDao tpDao = new TipoProdutoDaoJpa(manager);
		String resultado = tpDao.pegarDadosParaUmTipoProduto(id);
		return resultado;
	}
	

	public List<Fabricacao> getListaFabricacao() {
		EntityManager manager = this.getManager();
		FabricacaoDao fDao = new  FabricacaoDaoJpa(manager);
		this.listaFabricacao = fDao.getAll(Fabricacao.class);
		return listaFabricacao;
	}


	public List<Sabores> getListaSabores() {
		EntityManager manager = this.getManager();
		SaboresDao sDao = new SaboresDaoJpa(manager);
		this.listaSabores = sDao.getAll(Sabores.class);
		return listaSabores;
	}

	public List<TipoProduto> getListaTipoProdutos() {
		EntityManager manager = this.getManager();
		TipoProdutoDao tpDao = new TipoProdutoDaoJpa(manager);
		this.listaTipoProdutos = tpDao.getAll(TipoProduto.class);
		return listaTipoProdutos;
	}

	public List<Colaborador> getListaColaborador() {
		if (this.listaColaborador == null) {
			EntityManager manager = this.getManager();
			ColaboradorDao cDao = new ColaboradorDaoJpa(manager);
			this.listaColaborador = cDao.getAll(Colaborador.class);
		}
		return listaColaborador;
	}

	public BaseSorvete getUltimoDados() {
		EntityManager manager = this.getManager();
		BaseSorveteDao bsDao = new BaseSorveteDaoJpa(manager);
		this.baseSorvete = bsDao.selecionarUltimo();
		return baseSorvete;
	}
	
	public double getVolumeTotal(){
	this.baseSorvete =this.getUltimoDados();
	 double volumeSorvete =this.baseSorvete.getVolumeUnitario();
	  int base =this.fabricacao.getBase();
	  double resultado = volumeSorvete*base;
	  return resultado;
	}

	public List<Alerta> getListaAlerta() {
		EntityManager manager = this.getManager();
		AlertaDao aDao = new AlertaDaoJpa(manager);
		this.listaAleta = aDao.getAll(Alerta.class);
		return listaAleta;
	}
	
	 public String remove() {
			EntityManager manager = this.getManager();
			FabricacaoDao fDao = new FabricacaoDaoJpa(manager);
			fDao.remove(Fabricacao.class, fabricacao.getCodigo());
			return "/lista/pesquisa-fabricacao.xhtml";
			
			}
   
	  public List<Fabricacao> buscarNome(){
		     EntityManager manager = this.getManager();
		     FabricacaoDao fDao = new FabricacaoDaoJpa(manager);
			this.listaFabricacao = fDao.pegarNome(tipoProduto.getNome());   
		   return listaFabricacao;
		   
	}
	  
		public String preparaAlteracao() {
			EntityManager manager = this.getManager();
			FabricacaoDao fDao = new FabricacaoDaoJpa(manager);
			this.fabricacao = fDao.getById(Fabricacao.class, fabricacao.getCodigo());
			this.colaboradorId = this.fabricacao.getColaborador().getCondigo();
			this.saboresId= this.fabricacao.getSabores().getCodigo();
			this.tipoProdutoId = this.fabricacao.getTipoProduto().getCodigo();
			return "/cadastro/fabricacao.xhtml";
			}  
	  
	public void setListaSabores(List<Sabores> listaSabores) {
		this.listaSabores = listaSabores;
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

	public Sabores getSabores() {
		return sabores;
	}

	public void setSabores(Sabores sabores) {
		this.sabores = sabores;
	}

	public Fabricacao getFabricacao() {
		return fabricacao;
	}

	public void setFabricacao(Fabricacao fabricacao) {
		this.fabricacao = fabricacao;
	}

	public int getSaboresId() {
		return saboresId;
	}

	public int getTipoProdutoId() {
		return tipoProdutoId;
	}

	public void setSaboresId(int saboresId) {
		this.saboresId = saboresId;
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

	public BaseSorvete getBaseSorvete() {
		return baseSorvete;
	}

	public void setBaseSorvete(BaseSorvete baseSorvete) {
		this.baseSorvete = baseSorvete;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	  
   	
	

	   

}
