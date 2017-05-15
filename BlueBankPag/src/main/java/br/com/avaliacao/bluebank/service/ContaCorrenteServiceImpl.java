package br.com.avaliacao.bluebank.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.bluebank.dao.ContaCorrenteDao;
import br.com.avaliacao.bluebank.model.Cliente;
import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.repository.ContaCorrenteRepository;

@Service("contaCorrenteService")
public class ContaCorrenteServiceImpl implements ContaCorrenteService {

	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	
	@Autowired
	private ContaCorrenteDao contaCorrenteDao;

	@Override
	public ContaCorrente findByCliente(Cliente cliente) {
		return contaCorrenteRepository.findByCliente(cliente);
	}

	@Override
	public Collection<ContaCorrente> findAll() {
		return (Collection<ContaCorrente>) contaCorrenteRepository.findAll();
	}

	@Override
	public ContaCorrente findById(Long id) {
		return contaCorrenteRepository.findOne(id);
	}

	@Override
	public ContaCorrente obterContaPorAgenciaNumero(ContaCorrente contaCorrente) {
		return contaCorrenteDao.buscarPorAgenciaConta(contaCorrente);
	}

}
