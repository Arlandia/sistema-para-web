package br.com.gelateria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Alerta implements Serializable {

	@Id
	@GeneratedValue
	private Integer codigo;
	@Column(unique=true)
	private String codigo_alerta;
	private String nome;
	@Lob
	private String descricao;
    @ManyToMany(mappedBy="listaAlertaInsumos")
	private List<Insumo> listaInsumos;
    @ManyToMany(mappedBy="listaAlertaCompras")
    private List<Compra> listaCompras;
    @ManyToMany(mappedBy="listaAlertaFabricacao")
    private List<Fabricacao> listaFabricacao;
    @ManyToMany(mappedBy="listaAlertaProduto")
    private List<Produto> listaProduto;
   
    
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getCodigo_alerta() {
		return codigo_alerta;
	}
	public void setCodigo_alerta(String codigo_alerta) {
		this.codigo_alerta = codigo_alerta;
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
	public List<Compra> getListaCompras() {
		return listaCompras;
	}
	public void setListaCompras(List<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}
	public List<Insumo> getListaInsumos() {
		return listaInsumos;
	}
	public void setListaInsumos(List<Insumo> listaInsumos) {
		this.listaInsumos = listaInsumos;
	}
	public List<Fabricacao> getListaFabricacao() {
		return listaFabricacao;
	}
	public void setListaFabricacao(List<Fabricacao> listaFabricacao) {
		this.listaFabricacao = listaFabricacao;
	}
	public List<Produto> getListaProduto() {
		return listaProduto;
	}
	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
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
		Alerta other = (Alerta) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}
