package br.com.avaliacao.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.bluebank.model.TransacaoHistorico;

public interface TransacaoHistoricoRepository extends JpaRepository<TransacaoHistorico, Long> {

}
