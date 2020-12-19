package br.com.cin.sitcon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cin.sitcon.avaliacao.AvaliacaoDOD;
import br.com.cin.sitcon.model.Demanda;
import br.com.cin.sitcon.model.Situacao;
import br.com.cin.sitcon.repository.DodRepository;

@Service
public class DemandaService {

	@Autowired
	private DodRepository dodRepository;
	private List<Demanda> demandasPendentes;
	private List<Demanda> demandasParaAjustes;

	private AvaliacaoDOD avaliacaoDOD;

	public List<Demanda> listarDemandasPendentes() {

		this.demandasPendentes = dodRepository.findAll();

		return demandasPendentes.stream().filter(p -> p.getSituacao().equals(Situacao.ENVIADO_PARA_TIC.getDescricao()))
				.collect(Collectors.toList());

	}

	public List<Demanda> listarDemandasCriadas() {

		this.demandasPendentes = dodRepository.findAll();

		return demandasPendentes.stream().filter(p -> p.getSituacao().equals(Situacao.NOVO.getDescricao()))
				.collect(Collectors.toList());

	}

	public void setDemandasPendentes(List<Demanda> demandasPendentes) {
		this.demandasPendentes = demandasPendentes;
	}

	public List<Demanda> getDemandasPendentes() {
		return listarDemandasPendentes();
	}

	public List<Demanda> getDemandasNovas() {
		return listarDemandasCriadas();
	}

	public AvaliacaoDOD obterAvaliacaoDOD(Demanda demanda) {
		this.avaliacaoDOD = new AvaliacaoDOD(demanda);
		if (this.avaliacaoDOD.getDemanda().getObjeto().isEmpty()) {
			this.avaliacaoDOD.setObjetoConistente(Boolean.TRUE);
		} else {
			this.avaliacaoDOD.setObjetoConistente(Boolean.FALSE);
		}

		return this.avaliacaoDOD;
	}

	public Object getDemandasParaAjustes() {
		this.demandasParaAjustes = dodRepository.findAll();

		return demandasPendentes.stream().filter(p -> p.getSituacao().equals(Situacao.ENCAMINHADO_PARA_AJUSTES.getDescricao()))
				.collect(Collectors.toList());
	}

}
