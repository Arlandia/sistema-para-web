  package br.com.gelateria.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class Produto {
	
	@Id
	@GeneratedValue
	private int codigo;
	private double quantidade;
	private Calendar dataProduto;
	@Lob
	private String Observacao;
	@ManyToOne
	@JoinColumn(name="fabricacao_id")
	private Fabricacao fabricacao;
	@ManyToOne
	@JoinColumn(name="colaborador_id")
	private Colaborador colaborador;
	@ManyToMany
	@JoinTable(name="alerta_produto")
	private List<Alerta> listaAlertaProduto;


	
	
	
	public String getObservacao() {
		return Observacao;
	}
	public void setObservacao(String observacao) {
		Observacao = observacao;
	}
	
	public Calendar getDataProduto() {
		return dataProduto;
	}
	public void setDataProduto(Calendar dataProduto) {
		this.dataProduto = dataProduto;
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
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public Fabricacao getFabricacao() {
		return fabricacao;
	}
	public void setFabricacao(Fabricacao fabricacao) {
		this.fabricacao = fabricacao;
	}
	public List<Alerta> getListaAlertaProduto() {
		return listaAlertaProduto;
	}
	public void setListaAlertaProduto(List<Alerta> listaAlertaProduto) {
		this.listaAlertaProduto = listaAlertaProduto;
	}
	
	

}
