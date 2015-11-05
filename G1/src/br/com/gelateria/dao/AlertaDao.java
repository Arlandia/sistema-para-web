package br.com.gelateria.dao;

import java.util.List;

import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Fabricacao;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.Produto;

public interface AlertaDao extends Dao<Alerta, Integer> {
  
	public List<Alerta> pegarAlerta( String tipoAlerta);
	
	
	public Alerta porId(Integer id);
	
	public List<Insumo> ultimoAlertaInsumo(int id);
	
	public List<Fabricacao> ultimoAlertaFabricacao(int id);
	
	public List<Produto> ultimoAlertaProduto(int id);


	public List<Alerta> pegarNome(String nome);
	
	
}
