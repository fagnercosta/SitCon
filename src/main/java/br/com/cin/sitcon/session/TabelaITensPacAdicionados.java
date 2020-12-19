package br.com.cin.sitcon.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.cin.sitcon.model.ItemNecessidadeEssencial;
import br.com.cin.sitcon.model.ItemObjetivoEstrategico;
import br.com.cin.sitcon.model.ItemPAC;
import br.com.cin.sitcon.model.Necessidade;

@Component
@SessionScope
public class TabelaITensPacAdicionados {
	
	private List<ItemPAC> itens = new ArrayList<ItemPAC>();
	private ItemPAC itemPac;
	
	public void adicionarItemPac(String nome) {
		this.itemPac = new ItemPAC();
		this.itemPac.setDescricaoObjeto(nome);
		
		this.itens.add(this.itemPac);
		this.itemPac = new ItemPAC();
	}
	
	public void limpar() {
		this.itens = new ArrayList<>();
	}
	
	public List<ItemPAC> getItens() {
		return this.itens;
	}


}
