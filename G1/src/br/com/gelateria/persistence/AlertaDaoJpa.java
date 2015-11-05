package br.com.gelateria.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.AlertaDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Fabricacao;
import br.com.gelateria.model.Fornecedor;
import br.com.gelateria.model.Insumo;
import br.com.gelateria.model.Produto;

public class AlertaDaoJpa extends DaoJpa<Alerta, Integer> implements AlertaDao  {

	public AlertaDaoJpa(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Alerta> pegarAlerta(String tipoCodigo) {
		String consulta ="select a from Alerta a where a.codigo_alerta = :tipoCodigo";
		TypedQuery<Alerta> q = manager.createQuery(consulta, Alerta.class);
		q.setParameter("tipoCodigo", tipoCodigo);
		return  q.getResultList();
	}


	@Override
	public Alerta porId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Insumo> ultimoAlertaInsumo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fabricacao> ultimoAlertaFabricacao(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produto> ultimoAlertaProduto(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alerta> pegarNome(String nome) {
		String consulta = "select c from Alerta c where c.nome like :nome";
		TypedQuery<Alerta> q = this.manager.createQuery(consulta,Alerta.class);
		q.setParameter("nome","%" + nome +"%");
		return q.getResultList();
		
	}

}
