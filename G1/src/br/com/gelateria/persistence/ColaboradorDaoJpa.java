package br.com.gelateria.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.gelateria.dao.ColaboradorDao;
import br.com.gelateria.model.Alerta;
import br.com.gelateria.model.Cargo;
import br.com.gelateria.model.Colaborador;
import br.com.gelateria.util.Mensagens;


@Repository
public class ColaboradorDaoJpa  extends DaoJpa<Colaborador, Integer> implements ColaboradorDao{

	public ColaboradorDaoJpa(EntityManager tttmanager) {
		super(tttmanager);
		
	}
	
	@PersistenceContext
	private EntityManager manager;
	
	
	

	  public void setEntityManager(EntityManager manager) {
	        this.manager = manager;
	    }

	    public EntityManager getManager() {
	        return manager;
	    }

	@Override
	public String procurarPorNome(String nome) {
	
		return null;
	}

	@Override
	public List<Colaborador> pegarNome(String nome) {
		String consulta = "select c from Colaborador c where c.nome like :nome";
		TypedQuery<Colaborador> q = this.manager.createQuery(consulta,Colaborador.class);
		q.setParameter("nome","%" + nome +"%");
		return q.getResultList();
		
	}

	@Override
	@Transactional
	
	public Colaborador buscaLogin(String login) {
		System.err.println("----------"+login);
		String consulta ="select c from Colaborador c where c.login= :login";
		TypedQuery<Colaborador>  tq = this.manager.createQuery(consulta,Colaborador.class);
		tq.setParameter("login", login);
		return tq.getSingleResult();
	}

}
