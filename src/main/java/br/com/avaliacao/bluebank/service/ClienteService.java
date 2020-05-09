package br.com.avaliacao.bluebank.service;

import br.com.avaliacao.bluebank.model.Cliente;

public interface ClienteService {
	Cliente findByCpf(String cpf);
}
