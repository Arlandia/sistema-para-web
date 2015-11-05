package br.com.gelateria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Fornecedor {

	@Id
	@GeneratedValue
	private int codigo;
	private String nome;
	private String telefone;
	@OneToMany(mappedBy="fornecedor")
	private List<Compra > compra;
	
	
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<Compra> getCompra() {
		return compra;
	}
	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}
	
	
	
	
}
