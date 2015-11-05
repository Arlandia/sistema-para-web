package br.com.gelateria.dao;

import java.util.List;

import br.com.gelateria.model.Sabores;

public interface SaboresDao  extends Dao<Sabores , Integer> {

	public List<Sabores> buscarNome(String nome);

}
