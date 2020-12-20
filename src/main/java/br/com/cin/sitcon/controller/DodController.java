package br.com.cin.sitcon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cin.sitcon.avaliacao.AvaliacaoDOD;
import br.com.cin.sitcon.model.Demanda;
import br.com.cin.sitcon.model.ItemNecessidadeEssencial;

import br.com.cin.sitcon.model.Setor;
import br.com.cin.sitcon.model.Situacao;
import br.com.cin.sitcon.model.dto.NecessidadeDTO;
import br.com.cin.sitcon.repository.DodRepository;
import br.com.cin.sitcon.service.DemandaService;
import br.com.cin.sitcon.session.TabelaITensPacAdicionados;
import br.com.cin.sitcon.session.TabelaNecessidadesAdicionadas;
import br.com.cin.sitcon.session.TabelaObjetivosAdicionados;

@Controller
@RequestMapping("/demanda")
public class DodController {

	@Autowired
	DodRepository dodRepository;

	@Autowired
	TabelaNecessidadesAdicionadas necessidades;
	@Autowired
	TabelaObjetivosAdicionados objetivos;
	@Autowired
	TabelaITensPacAdicionados itensPac;
	@Autowired
	DemandaService demandaService;
	
	private String erros;

	@RequestMapping("/novo")
	public ModelAndView novo(Demanda demanda) {
		ModelAndView view = new ModelAndView("demanda/cadastroDemanda");
		view.addObject("demanda", demanda);
		view.addObject("pendentes", demandaService.getDemandasPendentes().size());
		view.addObject("setores", Setor.values());
		if (demanda.getId() == null) {
			this.necessidades.limpar();
		}
		return view;
	}

	/**@RequestMapping("/view")
	public ModelAndView resumo() {
		ModelAndView view = new ModelAndView("/demanda/pesquisar");
		view.addObject("demandas", this.dodRepository.findAll());
		view.addObject("pendentes", demandaService.getDemandasPendentes().size());
		return view;

	}***/
	
	@RequestMapping("/pendentes")
	public ModelAndView demandasPendentes() {
		ModelAndView view = new ModelAndView("/demanda/pesquisarDemandasPendentes");
		view.addObject("demandas", this.demandaService.getDemandasPendentes());
		view.addObject("pendentes", demandaService.getDemandasPendentes().size());
		return view;

	}
	
	@RequestMapping("/criadas")
	public ModelAndView demandasCriadas() {
		ModelAndView view = new ModelAndView("demanda/demandasCriadas");
		view.addObject("demandas", this.demandaService.getDemandasNovas());
		view.addObject("criadas", demandaService.getDemandasNovas().size());
		view.addObject("demandasAjustes", this.demandaService.getDemandasParaAjustes());
		return view;

	}
	
	@RequestMapping("/aprovadas")
	public ModelAndView demandasAprovadas() {
		ModelAndView view = new ModelAndView("/demanda/pesquisar");
		view.addObject("demandas", this.demandaService.getDemandasPendentes());
		view.addObject("pendentes", demandaService.getDemandasPendentes().size());
		return view;

	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Demanda demanda, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(demanda);
		}

		demanda.adicionarItensNecessidades(this.necessidades.getItens());
		demanda.adicionarItensObjetivos(this.objetivos.getItens());
		demanda.adicionarItensPac(this.itensPac.getItens());
		demanda.setSituacao(Situacao.NOVO.getDescricao());
		dodRepository.save(demanda);
		attributes.addFlashAttribute("message", "Documento Cadastrado com sucesso!");
		return new ModelAndView("redirect:/demanda/novo");
	}

	@GetMapping("/addNecessidade/{nome}")
	public ModelAndView adicionarNecessidade(@PathVariable("nome") String nome) {
		this.necessidades.adicionarNecessidade(nome);
		ModelAndView view = new ModelAndView("demanda/TabelaNecessidade");
		view.addObject("itens", this.necessidades.getItens());
		return view;

	}

	@GetMapping("/addObjetivo/{nome}")
	public ModelAndView adicionarObjetivo(@PathVariable("nome") String nome) {
		this.objetivos.adicionarObjetivo(nome);
		ModelAndView view = new ModelAndView("demanda/TabelaObjetivo");
		view.addObject("itens", this.objetivos.getItens());
		return view;

	}
	
	@GetMapping("/addItemPac/{nome}")
	public ModelAndView adicionarItemPac(@PathVariable("nome") String nome) {
		this.itensPac.adicionarItemPac(nome);
		ModelAndView view = new ModelAndView("demanda/TabelaPAC");
		view.addObject("itens", this.itensPac.getItens());
		return view;

	}
	
	@RequestMapping("/validadeItemPAC")
	public ModelAndView validadeItens() {
		this.erros = 
		"Não Foi Informado Nenhum Item!";
		ModelAndView view = new ModelAndView("fragments/MensagemErro");
		view.addObject("message",this.erros);
		this.erros = new String();
		return view;

	}
	
	@RequestMapping("**/limparValidate")
	public ModelAndView limparValidacao() {
		ModelAndView view = new ModelAndView("fragments/MensagemErro");
		this.erros = null;
		view.addObject("message",this.erros);
		return view;

	}
	
	
	
	@GetMapping("/view/{id}")
	public ModelAndView visualizar(@PathVariable("id") Integer id) {
		Demanda demanda = this.dodRepository.findById(id).get();
		ModelAndView view = new ModelAndView("demanda/viewDemandaNova");
		view.addObject("demanda", demanda);
		return view;

	}
	
	@GetMapping("/devolverParaAjustes/{id}")
	public ModelAndView devolverDemanda(@PathVariable("id") Integer id) {
		Demanda demanda = this.dodRepository.findById(id).get();
		demanda.setSituacao(Situacao.ENCAMINHADO_PARA_AJUSTES.getDescricao());
		dodRepository.save(demanda);
		
		return demandasPendentes();

	}

	@GetMapping("/enviarTIC/{id}")
	public ModelAndView enviarParaTic(@PathVariable("id") Integer id) {
		Demanda demanda = this.dodRepository.findById(id).get();
		demanda.setSituacao(Situacao.ENVIADO_PARA_TIC.getDescricao());
		this.dodRepository.save(demanda);
		return demandasCriadas();

	}
	
	@GetMapping("/avaliarDOD/{id}")
	public ModelAndView avaliarConformidadeDOD(@PathVariable("id") Integer id) {
		Demanda demanda = this.dodRepository.findById(id).get();
		AvaliacaoDOD avaliacao = new AvaliacaoDOD();
		avaliacao.setDemanda(demanda);
		
		ModelAndView view = new ModelAndView("demanda/avaliacaoDemanda");
		view.addObject("demanda",demanda);
		avaliacao.setObjetivoEstrategicos(demanda.getObjetivos());
		avaliacao.avaliarConsitenciaTexto(demanda.getObjetivos());
		avaliacao.avaliarConsitenciaTextoNecessidade(demanda.getNecessidades());
		view.addObject("objetivos",avaliacao.getObjetivoEstrategicos());
		view.addObject("necessidades", demanda.getNecessidades());
		return view;

	}

	@GetMapping("/removerNecessidade/{nome}")
	public ModelAndView removerNecessidade(@PathVariable("nome") String nome) {

		this.necessidades.removerNecessidade(nome);
		ModelAndView view = new ModelAndView("demanda/TabelaNecessidade");
		view.addObject("itens", this.necessidades.getItens());

		return view;

	}

	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView view = new ModelAndView("demanda/pesquisar");
		view.addObject(dodRepository.findAll());
		return view;

	}
}
