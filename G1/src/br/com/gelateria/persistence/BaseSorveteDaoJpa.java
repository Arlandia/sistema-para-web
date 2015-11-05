package br.com.gelateria.persistence;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.BaseSorveteDao;
import br.com.gelateria.model.BaseSorvete;

public class BaseSorveteDaoJpa extends DaoJpa<BaseSorvete, Integer> implements BaseSorveteDao {

	public BaseSorveteDaoJpa(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BaseSorvete selecionarUltimo() {
		 String consulta ="select bs from BaseSorvete bs order by bs.codigo desc";
		 TypedQuery<BaseSorvete> tq = manager.createQuery(consulta, BaseSorvete.class);
		 tq.setMaxResults(1);
		return tq.getSingleResult();
	}

	@Override
	public double volumeBaseSorvete() {
		String consulta="select bs.volumeUnitario from BaseSorvete bs order by bs.codigo desc";
		TypedQuery<Double> tq = manager.createQuery(consulta,Double.class);
		tq.setMaxResults(1);
		return tq.getSingleResult();
	}

}
