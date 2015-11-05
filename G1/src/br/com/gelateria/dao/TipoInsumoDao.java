package br.com.gelateria.dao;


import java.util.List;

import br.com.gelateria.model.TipoInsumo;

public interface TipoInsumoDao extends Dao<TipoInsumo, Integer> {

	
	public List<TipoInsumo> pegarNome(String nome);
	
	public List<TipoInsumo> pegarIdTipoÎnsumo();
	
	public List<Double> pegarQtdDeUmInsumo();
	

	

	
	
}
