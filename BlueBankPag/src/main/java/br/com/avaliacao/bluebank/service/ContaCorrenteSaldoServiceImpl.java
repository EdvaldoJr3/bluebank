package br.com.avaliacao.bluebank.service;

import br.com.avaliacao.bluebank.dao.ContaCorrenteDao;
import br.com.avaliacao.bluebank.model.Cliente;
import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.model.ContaCorrenteSaldo;
import br.com.avaliacao.bluebank.repository.ContaCorrenteRepository;
import br.com.avaliacao.bluebank.repository.ContaCorrenteSaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("contaCorrenteSaldoService")
public class ContaCorrenteSaldoServiceImpl implements ContaCorrenteSaldoService {

	@Autowired
	private ContaCorrenteSaldoRepository contaCorrenteSaldoRepository;

	@Override
	public void salvar(ContaCorrenteSaldo saldo) {
		contaCorrenteSaldoRepository.save(saldo);
	}
}
