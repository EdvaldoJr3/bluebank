package br.com.avaliacao.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.bluebank.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}
