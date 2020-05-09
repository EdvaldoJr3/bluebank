package br.com.avaliacao.bluebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.bluebank.model.Cliente;
import br.com.avaliacao.bluebank.repository.ClienteRepository;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente findByCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}

}
