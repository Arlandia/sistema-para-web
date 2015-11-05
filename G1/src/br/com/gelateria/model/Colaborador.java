package br.com.gelateria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Colaborador {
	
	@Id
	@GeneratedValue
	private int condigo;
	private String nome;
	private String telefone;
	@ManyToOne
	@JoinColumn(name="cargo_id")
	private Cargo cargo;
	@Column(unique=true)
	private String login;
	private String senha;
	@OneToMany(mappedBy="colaborador")
	private List<Compra> compra;
	@OneToMany(mappedBy="colaborador")
	private List<Fabricacao> fabricacao;
	@OneToMany(mappedBy="colaborador")
	private List<Produto> produto;
	
	
	
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getCondigo() {
		return condigo;
	}
	public void setCondigo(int condigo) {
		this.condigo = condigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public List<Compra> getCompra() {
		return compra;
	}
	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}
	public List<Fabricacao> getFabricacao() {
		return fabricacao;
	}
	public void setFabricacao(List<Fabricacao> fabricacao) {
		this.fabricacao = fabricacao;
	}
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
	
	
	
	
	

}
