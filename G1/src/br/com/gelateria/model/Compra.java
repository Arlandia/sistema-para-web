package br.com.gelateria.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Compra  implements Serializable{

	@Id
	@GeneratedValue
	private Integer codigo;
	private double valorTotal;
	private int itens;
	@Column(unique=true)
	private String numeroCupom;
	private Date dataCupom;
	private String confirmarChegada;
	private Date dataEntrega;
	@Lob
	private String Observacao;
	@ManyToOne
	@JoinColumn(name="fornecedor_id")
	private Fornecedor fornecedor;
	@ManyToOne
	@JoinColumn(name="colaborador_id")
	private Colaborador colaborador;
    @ManyToMany
    @JoinTable(name="alerta_compra")
    private List<Alerta> listaAlertaCompras;
    @OneToMany(mappedBy="compra")
    private List<Insumo> insumos;
    
    
	public String getObservacao() {
		return Observacao;
	}
	public void setObservacao(String observacao) {
		Observacao = observacao;
	}
	
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getItens() {
		return itens;
	}
	public void setItens(int itens) {
		this.itens = itens;
	}
	public String getNumeroCupom() {
		return numeroCupom;
	}
	public void setNumeroCupom(String numeroCupom) {
		this.numeroCupom = numeroCupom;
	}
	public Date getDataCupom() {
		return dataCupom;
	}
	public void setDataCupom(Date dataCupom) {
		this.dataCupom = dataCupom;
	}
	public String getConfirmarChegada() {
		return confirmarChegada;
	}
	public void setConfirmarChegada(String confirmarChegada) {	
		if(confirmarChegada.equals("Sim")){
		this.confirmarChegada = confirmarChegada;
		Date data = new Date(System.currentTimeMillis());     
		this.setDataEntrega(data);
		}else{
			this.confirmarChegada = confirmarChegada;
			 Date date = null;
			 this.setDataEntrega(date);	
					/* vai sempre dar erro pois nÃ£o exite data zerada String data = "0000-00-00"; 
					   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
					   formatter.setLenient(false); 
					 date = formatter.parse(data);
					 System.out.println(date);*/		
		}
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public List<Alerta> getListaAlertaCompras() {
		return listaAlertaCompras;
	}
	public void setListaAlertaCompras(List<Alerta> listaAlertaCompras) {
		this.listaAlertaCompras = listaAlertaCompras;
	}
	
	

	public List<Insumo> getInsumos() {
		return insumos;
	}
	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
    

	
	
	
	
	
	
	
}
