package br.com.avaliacao.bluebank.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.bluebank.model.Agencia;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
	Collection<Agencia> findByNumero(Long numero);
}
