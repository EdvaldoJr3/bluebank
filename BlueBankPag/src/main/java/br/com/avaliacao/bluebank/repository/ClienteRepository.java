package br.com.avaliacao.bluebank.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.bluebank.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Cliente findByCpf(String cpf);
	Collection<Cliente> findByNome(String nome);
	Collection<Cliente> findByEmail(String cpf);
}