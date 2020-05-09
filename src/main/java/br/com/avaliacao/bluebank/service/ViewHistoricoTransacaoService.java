package br.com.avaliacao.bluebank.service;

import java.util.Collection;

import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.model.ViewHistoricoTransacao;

public interface ViewHistoricoTransacaoService {

	public Collection<ViewHistoricoTransacao> findByConta(ContaCorrente conta);
	
}
