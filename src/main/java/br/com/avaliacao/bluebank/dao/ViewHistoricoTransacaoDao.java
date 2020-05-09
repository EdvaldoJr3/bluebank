package br.com.avaliacao.bluebank.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.model.ViewHistoricoTransacao;

@Repository
public class ViewHistoricoTransacaoDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public Collection<ViewHistoricoTransacao> findByConta(ContaCorrente conta) {
		
		StringBuilder query = new StringBuilder();
		
		query.append(" select v from ViewHistoricoTransacao v");
		query.append(" where v.contaId = :contaId");
		query.append(" order by v.dataOperacao desc ");
		
		Query queryTQ = em.
				createQuery(query.toString());
		
		queryTQ.setParameter("contaId", conta.getId());
		
		return queryTQ.getResultList();
	}

}
