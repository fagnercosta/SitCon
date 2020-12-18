package br.com.cin.sitcon.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.cin.sitcon.model.ItemNecessidadeEssencial;
import br.com.cin.sitcon.model.Necessidade;

@Component
@SessionScope
public class TabelaNecessidadesAdicionadas {
	
	private List<ItemNecessidadeEssencial> itens = new ArrayList<ItemNecessidadeEssencial>();
	private ItemNecessidadeEssencial necessidade;
	
	public void adicionarNecessidade(String nome) {
		this.necessidade = new ItemNecessidadeEssencial();
		this.necessidade.setDescricaoDetalhada(nome);
		
		this.itens.add(this.necessidade);
		this.necessidade = new ItemNecessidadeEssencial();
	}
	
	public void limpar() {
		this.itens = new ArrayList<>();
	}
	
	public List<ItemNecessidadeEssencial> getItens() {
		return this.itens;
	}

	public void removerNecessidade(String nome) {
		System.out.println("Tamanho da Lista"+this.getItens().size());
		int count =0;
		for(ItemNecessidadeEssencial necessidade : this.getItens()) {
			if(necessidade.getDescricaoDetalhada().equalsIgnoreCase(nome)) {
				this.itens.remove(count);
			}
			count++;
		}
		
	}

}
