package br.com.gelateria.dao;

import java.util.List;

import br.com.gelateria.model.Produto;

public interface ProdutoDao extends Dao<Produto, Integer> {
	
	
	public List<Produto> tresUltimasProducao();
	
	public Produto ultimaProducao();
	
	public Produto totalProducaoOntem();

	public List pegarQta(double quantidade);

}
