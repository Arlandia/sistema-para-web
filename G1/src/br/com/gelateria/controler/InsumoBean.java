package br.com.gelateria.controler;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.dao.CompraDao;
import br.com.gelateria.dao.FornecedorDao;
import br.com.gelateria.dao.InsumoDao;
import br.com.gelateria.dao.TipoInsumoDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Fornecedor;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.TipoInsumo;
import br.com.gelateria.persistence.AlertaDaoJpa;
import br.com.gelateria.persistence.CompraDaoJpa;
import br.com.gelateria.persistence.FornecedorDaoJpa;
import br.com.gelateria.persistence.InsumoDaoJpa;
import br.com.gelateria.persistence.TipoInsumoDaoJpa;
import br.com.gelateria.util.Mensagens;

@ManagedBean
public class InsumoBean {
	
	private Insumo insumo;
	private Alerta  alerta;
	private Alerta  alertaId;
	private Compra compra;
	private TipoInsumo tipoInsumo;
	private int tipoInsumoId;
	private List<Insumo> listaInsumo;
	private List<Alerta> listaAleta;
	private List<Compra> listaCompra;
	private Mensagens mensagens;
	private int compraId;
	private double valorUnitario;
 

	@PersistenceContext
	private  EntityManager entityManager;
	
	public InsumoBean(){
		this.insumo = new Insumo();
		this.tipoInsumo = new TipoInsumo();
		this.mensagens = new Mensagens();
		
	}
	

	/* public EntityManager getManager(){
	    	FacesContext fc =  FacesContext.getCurrentInstance();
	    	ExternalContext ec = fc.getExternalContext();
	    	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	    	return (EntityManager) request.getAttribute("EntityManager");		    	
	    }*/
	
	  public void setEntityManager(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }

	    public EntityManager getManager() {
	        return entityManager;
	    }

	    
	    
	public String inserir() {
		EntityManager manager = this.getManager();
		InsumoDao iDao = new InsumoDaoJpa(manager);
		TipoInsumoDao tiDao = new TipoInsumoDaoJpa(manager);
		CompraDao cDao = new CompraDaoJpa(manager);
		if (this.tipoInsumoId != 0) {
			TipoInsumo ti = tiDao.getById(TipoInsumo.class, tipoInsumoId);
			this.insumo.setTipoInsumo(ti);
			this.tipoInsumo = ti;
			Compra c = cDao.getById(Compra.class, compraId);
			this.insumo.setCompra(c);
			

			/*
			 * Como pegar dados manytomany e enviar e inseri-los List<Compra>
			 * cmp =cDao.pegaCompra(compraId); this.insumo.setListaCompra(cmp);
			 */
		}
		int idInsumo = getCompraView().getCodigo();
		// para evitar que seja colocado insumos além da quantidade de itens
		if (this.qtaIdCompraId(idInsumo) == getCompraView().getItens()) {
			return "/cadastro/compra.xhtml";
		} else {
			this.insumo.setValorTotal(this.valorUnitario);
			this.valorGrama();
			this.insumo.setCompra(getCompraView());
			iDao.save(insumo);
			// se depois de salvar a quantidade de insumo for igual a dos itens
			if (this.qtaIdCompraId(idInsumo) == getCompraView().getItens()) {
				this.mensagens.info();
				return "/lista/pesquisa_compra.xhtml";
			} else {
				this.mensagens.infoInsumo();
				return "/cadastro/insumo.xhtml";
			}

		}

	}

	// verifica a quantidade de id no insumo que seja referente a compra
	private Long qtaIdCompraId(int id) {
		EntityManager manager = this.getManager();
		InsumoDao iDao = new InsumoDaoJpa(manager);
		long qtaId = iDao.idCompraInsumo(id);
		return qtaId;
	}

	public void valorGrama() {
		double vt = this.insumo.getValorTotal();
		double p = this.insumo.getPesoTotal();
		double pegar = vt / p;
		this.insumo.setValorGrama(pegar);
		System.out.println("vt" + vt + ", p" + p + ",pegar" + pegar);
	}

	public List<Insumo> getListaInsumo() {
		if (this.listaInsumo == null) {
			EntityManager manager = this.getManager();
			InsumoDao iDao = new InsumoDaoJpa(manager);
			this.listaInsumo = iDao.getAll(Insumo.class);
		}
		return listaInsumo;
	}

	public List<Alerta> getListaAlerta() {
		EntityManager manager = this.getManager();
		AlertaDao aDao = new AlertaDaoJpa(manager);
		this.listaAleta = aDao.getAll(Alerta.class);
		return listaAleta;
	}

	public List<Compra> getListaCompra() {
		EntityManager manager = this.getManager();
		CompraDao cDao = new CompraDaoJpa(manager);
		this.listaCompra = cDao.getAll(Compra.class);
		return listaCompra;
	}

	// pegando o dados da ultimo registro de compra
	public Compra getCompraView() {
		EntityManager manager = this.getManager();
		CompraDao cDao = new CompraDaoJpa(manager);
		this.compra = cDao.pegarUltimoIdCompra();
		return compra;
	}

	// para habilitar o valor conforme a quantidade de itens
	public String getHalitaComponenteValor() {
		if (this.getCompraView().getItens() == 1) {
			return "true";
		} else {
			return "false";
		}
	}
	


	// calcula o valor unitario do itens
	private double calcValorUnitario() {
		double cVT = this.getCompraView().getValorTotal();
		double iVT = this.insumo.getValorTotal();
		double valor = cVT - iVT;
		return valor;

	}

	// verifica o id de compra igual a qtd de itens e faz o calculo dos itens,
	// podendo não ser o correto porém o ultimo valor deve ser ao do item
	public double getValorUnitario() {
		int idInsumo = getCompraView().getCodigo();
		/* long verificaQta = this.qtaIdCompraId(idInsumo); */
		if (this.getCompraView().getItens() == 1) {
			double valor1 = this.getCompraView().getValorTotal();
			return this.valorUnitario = valor1;
		} else {
			double valor2 = this.calcValorUnitario();
			return this.valorUnitario = valor2;
		}
	}
	
		
	   
    public void remove(){
    	EntityManager manager = this.getManager();
    	InsumoDao iDao = new InsumoDaoJpa(manager);
	    iDao.remove(Insumo.class, insumo.getCodigo());
	     this.mensagens.infoExclusao();
	    /* return "/lista/pesquisa_fornecedor.xhtml";*/
    }
    
    public String preparaAlteracao(){
    	 EntityManager manager = this.getManager();
    	 InsumoDao iDao = new InsumoDaoJpa(manager);
	     iDao.getById(Insumo.class,insumo.getCodigo());
	     this.tipoInsumoId = this.insumo.getTipoInsumo().getCodigo();
	     return "/cadastro/insumo.xhtml";
	     
    }
    
    public List<Insumo> buscarNome(){
	     EntityManager manager = this.getManager();
	     InsumoDao iDao = new InsumoDaoJpa(manager);
		this.listaInsumo = iDao.pegarNome(insumo.getNome());   
	   return listaInsumo;
	   
  }

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Alerta getAlerta() {
		return alerta;
	}

	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}

	public Alerta getAlertaId() {
		return alertaId;
	}

	public void setAlertaId(Alerta alertaId) {
		this.alertaId = alertaId;
	}

	public TipoInsumo getTipoInsumo() {
		return tipoInsumo;
	}

	public void setTipoInsumo(TipoInsumo tipoInsumo) {
		this.tipoInsumo = tipoInsumo;
	}

	public void setListaInsumo(List<Insumo> listaInsumo) {
		this.listaInsumo = listaInsumo;
	}

	public int getCompraId() {
		return compraId;
	}

	public void setCompraId(int compraId) {
		this.compraId = compraId;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public int getTipoInsumoId() {
		return tipoInsumoId;
	}

	public void setTipoInsumoId(int tipoInsumoId) {
		this.tipoInsumoId = tipoInsumoId;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/*
	 * para umm selectonemenu precisa de objeto por isso utilizei esse getCompra
	 * para chamar na View public Compra getCompra() { return compra; }
	 * 
	 * public void setCompra(Compra compra) { this.compraId =
	 * compra.getCodigo(); }
	 * 
	 * public int getCompraId() { return compraId; }
	 * 
	 * public void setCompraId(Compra compra) { this.compraId =
	 * compra.getCodigo(); }
	 */

		
       
		


}
