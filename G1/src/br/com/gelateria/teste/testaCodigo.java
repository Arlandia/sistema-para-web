package br.com.gelateria.teste;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.expression.impl.ThisExpressionResolver;

import br.com.gelateria.model.Cargo;
import br.com.gelateria.model.Colaborador;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.TipoInsumo;



public class testaCodigo  {
	
	public static void main(String[] args) {
		
	/* Cargo cg = new Cargo();	
	 ClasseTestada ct = new ClasseTestada();
	cg =   ct.pega("Admini");
	 
			System.out.println(cg.getDescricao()+","+cg.getNome());
		*/
		
		
	ClasseTestada ct = new ClasseTestada();
	/*
	ct.ct();*/
	/*	List<Insumo> i = new ArrayList<Insumo>();
  for(Insumo a : i){
	  System.err.println(a.getNome());
  }*/
  
/*  ct.Restricao1();
  System.out.println("------------------------------");

    String i= ct.pegarUltimoIdColaborador();
  
		System.err.println(i.toString()+","+i);
		System.out.println("------------------------------");*/
	long t = ct.idCompraInsumo(4);
	System.err.println(t);
	
	ClasseTestada_2 ct2 = new ClasseTestada_2();
	//System.err.println("valordevezesfor"+ct2.qtaDeIdEmTipoInsumo());
	//System.err.println("maiorValor"+ct2.maiorValor());
	//System.err.println("maiorValorId"+ct2.maiorValorId());
	//System.err.println("mairValorNome"+ct2.nomeTipoInsumo(ct2.maiorValorId()));
	//System.err.println("menorValor"+ct2.menorValor());
	//System.err.println("menorId"+ct2.menorValorId());
	//System.err.println("menorValorNome"+ct2.nomeTipoInsumo(ct2.menorValorId()));
	
/*		List<Insumo> listaInsumo;
		listaInsumo = ct2.pegarNome("Liga");
		for (Insumo insumo : listaInsumo) {
			System.err.println(insumo.getNome());
		}*/	
/*	System.err.println("lista1");
	List<TipoInsumo> lista1;
	lista1 = ct2.pegarIdTipoÎnsumo();
	for (TipoInsumo integer : lista1) {
		System.err.println("lista1"+integer.getCodigo());
		System.err.println("--------------------------");
		System.err.println("lista1"+integer.getNome());
	}
	
	
	System.err.println("lista2");
	List<Double> lista2;
   lista2= (List<Double>) ct2.pegarQtdDeUmInsumo();
   for (Double double1 : lista2) {
	   System.err.println("id 1: "+double1);
	
}
   
   
   System.err.println("Maior Valor");
   System.err.println("Maior Valor:"+ct2.maiorValorMaior()+"Menor Valor:"+ct2.menorValorMenor()+"Menor Valor Insumo:"+ct2.menorValorInsumo()+","+ct2.ValoInsumo(ct2.menorValorInsumo()).getTipoInsumo().getNome()+"Menor Valor Insumo:"+ct2.maiorValorInsumo()+","+ct2.ValoInsumo(ct2.maiorValorInsumo()).getTipoInsumo().getNome());
   
  
   */

 Colaborador c =ct2.buscaLogin("alvino_santos");
	System.err.println(c.getNome());
	
	
/*	ct2.testa();*/
	System.out.println(ct2.pegarUltimoIdCompra().getCodigo());
	ct2.pegarUltimoIdCompra().getCodigo();
 	
	}

}
