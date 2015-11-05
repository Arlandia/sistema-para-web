
package br.com.gelateria.model;

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
import javax.persistence.OneToOne;


@Entity
public class Fabricacao {
	
	@Id
	@GeneratedValue
	private int codigo;
	@ManyToOne
	@JoinColumn(name="tipoProduto_id")
	private TipoProduto tipoProduto;
    @Lob
	private String Observacao;
    private int base;
	@ManyToOne
	@JoinColumn(name="colaborador_id")
	private Colaborador colaborador;
	@OneToMany(mappedBy ="fabricacao")
	private List<Produto> listaProduto;
	@ManyToMany
	@JoinTable(name="alerta_fabricacao")
	private List<Alerta> listaAlertaFabricacao;
	@ManyToOne
	@JoinColumn(name="sabores_id")
	private Sabores Sabores;
    private double volumeTotal;
	
	
	
	
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
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public List<Produto> getListaProduto() {
		return listaProduto;
	}
	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	public List<Alerta> getListaAlertaFabricacao() {
		return listaAlertaFabricacao;
	}
	public void setListaAlertaFabricacao(List<Alerta> listaAlertaFabricacao) {
		this.listaAlertaFabricacao = listaAlertaFabricacao;
	}
	public int getBase() {
		return base;
	}
	public void setBase(int base) {
		this.base = base;
	}
	
	public Sabores getSabores() {
		return Sabores;
	}
	public void setSabores(Sabores sabores) {
		Sabores = sabores;
	}
	
	public double getVolumeTotal() {
		return volumeTotal;
	}
	public void setVolumeTotal(double volumeTotal) {
		this.volumeTotal = volumeTotal;
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
		Fabricacao other = (Fabricacao) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	

	
	
	

}
