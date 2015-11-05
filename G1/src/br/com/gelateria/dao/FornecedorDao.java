package br.com.gelateria.dao;

import java.io.Serializable;
import java.util.List;

import br.com.gelateria.model.Fornecedor;

public interface FornecedorDao extends Dao<Fornecedor, Integer> {
	
	
	public List<Fornecedor> pegarNome(String nome); 

}
