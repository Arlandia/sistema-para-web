package br.com.gelateria.dao;

import java.util.List;

import br.com.gelateria.model.Fabricacao;

public interface FabricacaoDao extends Dao<Fabricacao,Integer> {

	
   public Fabricacao pegarUltimoId();
   
   public List<Fabricacao> buscarTresUltimoFabricacao();
   
   public double fabricacaoTotalSorvete(String tipoProduto);

public List<Fabricacao> pegarNome(String nome);
   
	
	
}
