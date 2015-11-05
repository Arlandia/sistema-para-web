package br.com.gelateria.dao;

import br.com.gelateria.model.BaseSorvete;

public interface BaseSorveteDao  extends Dao<BaseSorvete, Integer>{
	
	
	
	
	public BaseSorvete selecionarUltimo();
	
	public double volumeBaseSorvete();

}
