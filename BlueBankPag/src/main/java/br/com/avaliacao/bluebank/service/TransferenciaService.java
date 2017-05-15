package br.com.avaliacao.bluebank.service;

import br.com.avaliacao.bluebank.model.Transferencia;

public interface TransferenciaService {
	
	public String transferir(Transferencia transferencia);

	public Transferencia findById(Long transferenciaId);
}
