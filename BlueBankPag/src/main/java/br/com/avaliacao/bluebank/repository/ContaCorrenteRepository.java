package br.com.avaliacao.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.bluebank.model.ContaCorrente;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

}
