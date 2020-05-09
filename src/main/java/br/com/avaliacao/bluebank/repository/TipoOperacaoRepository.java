package br.com.avaliacao.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao.bluebank.model.TipoOperacao;

public interface TipoOperacaoRepository extends JpaRepository<TipoOperacao, Long> {

}
