package br.com.gelateria.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.gelateria.dao.CompraDao;
import br.com.gelateria.model.Compra;
import br.com.gelateria.model.Insumo;

public class CompraDaoJpa  extends DaoJpa<Compra , Integer> implements CompraDao{

	public CompraDaoJpa(EntityManager manager) {
		super(manager);
		
	}

	@Override
	public List<Compra> CompraOrdenada() {
		 String consulta ="select c from Compra c order by c.dataCupom";
		 TypedQuery<Compra> q = this.manager.createQuery(consulta, Compra.class);
		return q.getResultList();
	}

	@Override
	public Compra pegaNumeroCupom(Integer id) {
		return this.manager.find(Compra.class, id);
		
	}



	/*fazendo uma lista que pega todos os tados de uma tabela no banco
	@Override
	public List<Compra> pegarIdValido() {
		String consulta ="select c from  Compra c ";
		Query q = this.manager.createQuery(consulta, Compra.class);
		 return  q.getResultList();
		
	}*/
	
	/*pegando apenas uma unica linha da tabela atraves de parametro passado*/
	@Override
	public Compra pegarIdValido(Integer numeroCupom) {
		 return manager.find(Compra.class, numeroCupom);
		
	}

	@Override
	public List<Compra> pegaCompra(Integer codigo) {
		String consulta ="select c from  Compra c where c.codigo= :codigo";
		Query q = this.manager.createQuery(consulta, Compra.class);
		q.setParameter("codigo", codigo);
		 return (List<Compra>) q.getResultList();
		
	}

	@Override
	public Compra pegarUltimoIdCompra() {
		String consulta ="Select c from Compra c order by c.codigo desc";
		TypedQuery<Compra> tq =  manager.createQuery(consulta,Compra.class);
		tq.setMaxResults(1);
		return tq.getSingleResult();
	}

	@Override
	public List<Compra> pegarQtaItens(int qta) {
	 String  consulta = "select c from Compra c where c.itens= :itens ";
	 TypedQuery<Compra> tq = manager.createQuery(consulta,Compra.class );
	 tq.setParameter("itens", qta);
 		return tq.getResultList();
	}


	@Override
	public List<Compra> buscarNumeroCupom(String cupom) {
		String consulta ="select c from Compra c where c.numeroCupom like :cupom";
		 TypedQuery<Compra> tq = manager.createQuery(consulta,Compra.class );
		 tq.setParameter("cupom","%" + cupom + "%");
	 		return tq.getResultList();
	}
	
	


	/*pegando objet
	public List<Compra> pegarObjeto(Compra compra){
		String consulta ="select c from Compra c where c = :compra";
		Query q = this.manager.createQuery(consulta, Compra.class);
		q.setParameter("compra", compra);
		return q.getResultList();
	}
	*/
	
	

}
