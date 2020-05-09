package br.com.avaliacao.bluebank.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.bluebank.dao.ViewHistoricoTransacaoDao;
import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.model.ViewHistoricoTransacao;

@Service("viewHistoricoTransacaoService")
public class ViewHistoricoTransacaoServiceImpl implements ViewHistoricoTransacaoService {

	@Autowired
	private ViewHistoricoTransacaoDao viewHistoricoTransacaoDao;

	@Override
	public Collection<ViewHistoricoTransacao> findByConta(ContaCorrente conta) {
		return viewHistoricoTransacaoDao.findByConta(conta);
	}


}
