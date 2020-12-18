package br.com.cin.sitcon.model;

public enum Setor {

	PRODIN("Pró Reitoria de Desenvolvimento Institucional"),
	PROEN("Pró Reitoria de Ensino"),
	GESTAO_PESSOAS("Diretoria de Gestão de Pessoas");
	
	private String descricao;
	
	Setor(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() { 
		return descricao;
	}
}
