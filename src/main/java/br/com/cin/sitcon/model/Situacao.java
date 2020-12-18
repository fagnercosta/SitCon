package br.com.cin.sitcon.model;

public enum Situacao {

	
	NOVO("NOVO"),
	APROVADO("APROVADO"),
	ENCAMINHADO_PARA_AJUSTES("EMCAMINHADO PARA AJUSTES"),
	REPROVADO("REPROVADO"),
	ENVIADO_PARA_ADMISTRACAO("ENVIADO PARA ÁREA ADMISTRATIVA"),
	ENVIADO_PARA_TIC("AGUARDANDO ANÁLISE DA ÁREA DE TIC"),
	CANCELADO("CANCELADO");
	
	private String descricao;
	
	Situacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() { 
		return descricao;
	}
}
