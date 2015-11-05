package br.com.gelateria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Insumo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer codigo;
	private String nome;
	@Lob
	private String descricao;
	private double pesoTotal;
	private double valorTotal;
	private double valorGrama;
	@ManyToOne
	@JoinColumn(name="compra_id")
	private Compra compra;
	@ManyToOne
	@JoinColumn(name="tipoinsumo_id")
	private TipoInsumo tipoInsumo;
	@ManyToMany
	@JoinTable(name="alerta_insumo")
	private List<Alerta> listaAlertaInsumos;
    
   
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPesoTotal() {
		return pesoTotal;
	}
	public void setPesoTotal(double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public double getValorGrama() {
		return valorGrama;
	}
	public void setValorGrama(double valorGrama) {		
		this.valorGrama = valorGrama;
		
	}
	
	
	
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public TipoInsumo getTipoInsumo() {
		return tipoInsumo;
	}
	public void setTipoInsumo(TipoInsumo tipoInsumo) {
		this.tipoInsumo = tipoInsumo;
	}
	public List<Alerta> getListaAlertaInsumos() {
		return listaAlertaInsumos;
	}
	public void setListaAlertaInsumos(List<Alerta> listaAlertaInsumos) {
		this.listaAlertaInsumos = listaAlertaInsumos;
	}
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
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
		Insumo other = (Insumo) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	

}
