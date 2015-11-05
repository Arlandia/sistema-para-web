package br.com.gelateria.dao;

import java.util.List;

import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Insumo;

public interface CompraDao extends Dao<Compra,Integer> {

	/*fazendo uma lista que pega todos os dados de uma tabela no banco
	public List<Compra> pegarIdValido( );*/

	/*pegando apenas uma unica linha da tabela atraves de parametro passado
	public List<Compra> pegarIdValido(String cupom);*/
	
	/*pegando objeto
	public List<Compra> pegarObjeto(Compra c);
*/	
	
	public List<Compra> CompraOrdenada();
	
	public Compra pegaNumeroCupom(Integer id);
	
	public Compra pegarIdValido(Integer numeroCupom);
	
	public List<Compra> pegaCompra(Integer id);
	
	public Compra pegarUltimoIdCompra();
	
	public List<Compra> pegarQtaItens(int qta);
	
	public List<Compra> buscarNumeroCupom(String cupom);
	
	
 
	
}
