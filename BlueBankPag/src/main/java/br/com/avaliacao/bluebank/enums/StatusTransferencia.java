package br.com.avaliacao.bluebank.enums;

public enum StatusTransferencia {
	SUCESSO(1, "Sucesso"),
	ERRO(0, "Erro");
	
	public int valor;
	public String descricao;
	
	
	 StatusTransferencia(int valor, String descricao){
		this.valor = valor;
		this.descricao = descricao;
	}
}
