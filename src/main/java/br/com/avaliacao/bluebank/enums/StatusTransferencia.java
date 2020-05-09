package br.com.avaliacao.bluebank.enums;

public enum StatusTransferencia {
	
	ERRO(0, "Erro"),
	SUCESSO(1, "Sucesso");
	
	public int valor;
	public String descricao;
	
	
	 StatusTransferencia(int valor, String descricao){
		this.valor = valor;
		this.descricao = descricao;
	}
}
