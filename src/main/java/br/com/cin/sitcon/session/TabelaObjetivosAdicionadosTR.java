package br.com.cin.sitcon.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.cin.sitcon.model.ItemNecessidadeEssencial;
import br.com.cin.sitcon.model.ItemObjetivoEstrategico;
import br.com.cin.sitcon.model.Necessidade;

@Component
@SessionScope
public class TabelaObjetivosAdicionadosTR {
	
	private List<ItemObjetivoEstrategico> itens = new ArrayList<ItemObjetivoEstrategico>();
	private ItemObjetivoEstrategico objetivo;
	
	public void adicionarObjetivo(String nome) {
		this.objetivo = new ItemObjetivoEstrategico();
		this.objetivo.setDescricaoObjetivo(nome);
		
		this.itens.add(this.objetivo);
		System.out.println("Adicionando objetivos.....");
		this.objetivo = new ItemObjetivoEstrategico();
	}
	
	public void limpar() {
		this.itens = new ArrayList<>();
	}
	
	public List<ItemObjetivoEstrategico> getItens() {
		return this.itens;
	}


}
