package br.com.gelateria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class TipoInsumo {

	@Id
	@GeneratedValue
	private int codigo;
	private String nome;
	@OneToMany(mappedBy="tipoInsumo")
	private List<Insumo> insumo;
	@ManyToMany
	@JoinTable(name="tipoinsumo_tipoproduto")
	private List<TipoProduto> listaTipoInsumoTipoProduto;
	
	
	
	public List<Insumo> getInsumo() {
		return insumo;
	}
	public void setInsumo(List<Insumo> insumo) {
		this.insumo = insumo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<TipoProduto> getListaTipoInsumoTipoProduto() {
		return listaTipoInsumoTipoProduto;
	}
	public void setListaTipoInsumoTipoProduto(
			List<TipoProduto> listaTipoInsumoTipoProduto) {
		this.listaTipoInsumoTipoProduto = listaTipoInsumoTipoProduto;
	}
	
	
	
	
	
	
	
	
	
}
