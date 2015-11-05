package br.com.gelateria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class TipoProduto {

	@Id
	@GeneratedValue
	private int codigo;
	private String nome;
	@OneToMany(mappedBy="tipoProduto")
	private List<Fabricacao> fabricacao;
	@ManyToMany(mappedBy="listaTipoInsumoTipoProduto")
    private List<TipoInsumo> listaTipoInsumo;
	
	
	

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
	public List<Fabricacao> getFabricacao() {
		return fabricacao;
	}
	public void setFabricacao(List<Fabricacao> fabricacao) {
		this.fabricacao = fabricacao;
	}
	public List<TipoInsumo> getListaTipoInsumo() {
		return listaTipoInsumo;
	}
	public void setListaTipoInsumo(List<TipoInsumo> listaTipoInsumo) {
		this.listaTipoInsumo = listaTipoInsumo;
	}
	
	
	
	
}
