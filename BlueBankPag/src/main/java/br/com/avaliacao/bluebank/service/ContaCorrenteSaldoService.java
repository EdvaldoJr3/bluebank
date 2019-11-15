package br.com.avaliacao.bluebank.service;

import br.com.avaliacao.bluebank.model.Cliente;
import br.com.avaliacao.bluebank.model.ContaCorrente;
import br.com.avaliacao.bluebank.model.ContaCorrenteSaldo;

import java.util.Collection;

public interface ContaCorrenteSaldoService {
    public void salvar(ContaCorrenteSaldo saldo);
}
