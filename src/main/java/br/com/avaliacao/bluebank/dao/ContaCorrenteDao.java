package br.com.avaliacao.bluebank.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.avaliacao.bluebank.model.ContaCorrente;

@Repository
public class ContaCorrenteDao {

	@PersistenceContext
	private EntityManager em;

	public ContaCorrente buscarPorAgenciaConta(ContaCorrente contaCorrente) {
		
		StringBuilder query = new StringBuilder();
		
		ContaCorrente contaCorrenteResultado;
		
		query.append(" select c from ContaCorrente c, Agencia a");
		query.append(" where c.agenciaId = a.id ");
		query.append(" and c.numero = :numero ");
		query.append(" and c.digito = :digito ");
		query.append(" and a.numero = :agenciaNumero ");
		query.append(" and a.digito = :agenciaDigito ");
		
		Query queryTQ = em.
				createQuery(query.toString());
		
		queryTQ.setParameter("numero", contaCorrente.getNumero());
		queryTQ.setParameter("digito", contaCorrente.getDigito());
		queryTQ.setParameter("agenciaNumero", contaCorrente.getAgencia().getNumero());
		queryTQ.setParameter("agenciaDigito", contaCorrente.getAgencia().getDigito());
		
		try {
			contaCorrenteResultado = (ContaCorrente) queryTQ.getSingleResult();
		} catch (NoResultException e) {
			 contaCorrenteResultado = new ContaCorrente();
		}
		
		return contaCorrenteResultado;
	}

}
