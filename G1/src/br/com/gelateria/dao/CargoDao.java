package br.com.gelateria.dao;

import java.util.List;

import br.com.gelateria.model.Cargo;

public interface CargoDao  extends Dao<Cargo, Integer>{

	
	public List<Cargo> pegarNome(String nome);
	
	
	
}
