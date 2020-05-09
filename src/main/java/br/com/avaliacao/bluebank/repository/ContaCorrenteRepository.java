package br.com.avaliacao.bluebank.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.avaliacao.bluebank.model.Cliente;
import br.com.avaliacao.bluebank.model.ContaCorrente;

public interface ContaCorrenteRepository extends CrudRepository<ContaCorrente, Long> {
	ContaCorrente findByCliente(Cliente cliente);
}
