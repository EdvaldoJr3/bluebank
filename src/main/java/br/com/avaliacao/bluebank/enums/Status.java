package br.com.avaliacao.bluebank.enums;

public enum Status {

	INATIVO(0, "Inativo"),
	ATIVO(1, "Ativo");
	
	public int valor;
	public String descricao;
	
	
	 Status(int valor, String descricao){
		this.valor = valor;
		this.descricao = descricao;
	}
	 
}
