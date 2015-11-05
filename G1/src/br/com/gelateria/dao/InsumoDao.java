package br.com.gelateria.dao;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.TipoInsumo;

public interface InsumoDao  extends Dao<Insumo,Integer>{

	
	
	
	  /*public List<Insumo> listaJoin();*/
	
	public Long idCompraInsumo(int id);
	
	
	//para o controler home
	// public List<Insumo> maiorEMenorEstoqueInsumo();
 

	public List<TipoInsumo> pegarIdTipoInsumo();
	
	public List<Double> pegarQtdDeUmInsumo();
	
	public double maiorValorMaior();
	
    public double  menorValorMenor();
		
    public Insumo  ValoInsumo(int id);
		
	public int maiorValorInsumo();
		
    public int  menorValorInsumo();

	public Insumo ultimoId();

	public List<Insumo> pegarNome(String nome);
	
	public int ultimoIdInsumo();
	
	public Double qtaDeUmTipoInsumo(int id);
	
   
   
}	