package br.com.cin.sitcon.controller;

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

import br.com.cin.sitcon.model.Demanda;
import br.com.cin.sitcon.model.ItemNecessidadeEssencial;

import br.com.cin.sitcon.model.Setor;
import br.com.cin.sitcon.model.Situacao;
import br.com.cin.sitcon.model.dto.NecessidadeDTO;
import br.com.cin.sitcon.repository.DodRepository;
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

	@RequestMapping("/novo")
	public ModelAndView novo(Demanda demanda) {
		ModelAndView view = new ModelAndView("demanda/cadastroDemanda");
		view.addObject("demanda", demanda);
		view.addObject("setores", Setor.values());
		if (demanda.getId() == null) {
			this.necessidades.limpar();
		}
		return view;
	}

	@RequestMapping("/view")
	public ModelAndView resumo() {
		ModelAndView view = new ModelAndView("/demanda/pesquisar");
		view.addObject("demandas", this.dodRepository.findAll());
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

	@GetMapping("/enviarTIC/{id}")
	public ModelAndView enviarParaTic(@PathVariable("id") Integer id) {
		Demanda demanda = this.dodRepository.findById(id).get();
		demanda.setSituacao(Situacao.ENVIADO_PARA_TIC.getDescricao());
		this.dodRepository.save(demanda);
		return resumo();

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
