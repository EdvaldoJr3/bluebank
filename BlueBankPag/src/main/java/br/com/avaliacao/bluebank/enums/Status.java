package br.com.avaliacao.bluebank.enums;

public enum Status {

	ATIVO(1, "Ativo"),
	INATIVO(0, "Inativo");
	
	public int valor;
	public String descricao;
	
	
	 Status(int valor, String descricao){
		this.valor = valor;
		this.descricao = descricao;
	}
	 
}
