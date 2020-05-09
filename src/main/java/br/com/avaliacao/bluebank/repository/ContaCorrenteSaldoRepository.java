package br.com.avaliacao.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.model.ContaCorrenteSaldo;

public interface ContaCorrenteSaldoRepository extends JpaRepository<ContaCorrenteSaldo, Long> {
	ContaCorrenteSaldo findByContaCorrente(ContaCorrente contaCorrente);
}
