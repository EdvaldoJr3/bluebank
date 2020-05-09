package br.com.avaliacao.bluebank.service;

import java.util.Collection;

import br.com.avaliacao.bluebank.model.Cliente;
import br.com.avaliacao.bluebank.model.ContaCorrente;

public interface ContaCorrenteService {
	
	public Collection<ContaCorrente> findAll();
	public ContaCorrente findByCliente(Cliente cliente);
	public ContaCorrente findById(Long id);
	public ContaCorrente obterContaPorAgenciaNumero(ContaCorrente contaDestino);
}
