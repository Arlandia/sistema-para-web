package br.com.gelateria.dao;

import java.util.List;

import br.com.gelateria.model.TipoProduto;

public interface TipoProdutoDao  extends Dao<TipoProduto , Integer> {

	
	public String pegarDadosParaUmTipoProduto(int id);
	
	public List<TipoProduto> pegarNome(String nome);
	

}
