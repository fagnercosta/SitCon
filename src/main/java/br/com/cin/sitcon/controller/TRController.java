package br.com.cin.sitcon.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cin.sitcon.avaliacao.AvaliacaoDOD;
import br.com.cin.sitcon.model.Demanda;
import br.com.cin.sitcon.model.ItemObjetivoEstrategico;
import br.com.cin.sitcon.model.Setor;
import br.com.cin.sitcon.model.Situacao;
import br.com.cin.sitcon.model.TermoRereferencia;
import br.com.cin.sitcon.repository.TermoReferenciaRepository;
import br.com.cin.sitcon.session.TabelaNecessidadesAdicionadasTR;
import br.com.cin.sitcon.session.TabelaObjetivosAdicionadosTR;

@Controller
@RequestMapping("/projeto")
public class TRController {
	
	@Autowired
	private TabelaObjetivosAdicionadosTR objetivos;
	@Autowired
	private TabelaNecessidadesAdicionadasTR necessidades;
	@Autowired
	private TermoReferenciaRepository termoReferenciaRepository;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(TermoRereferencia  termo) {
		ModelAndView view = new ModelAndView("tr/cadastroTR");
		view.addObject("termo", termo);
		if(termo.getId()==null) {
			this.objetivos.limpar();
			this.necessidades.limpar();
		}
		return view;
		
	}
	
	@RequestMapping("/list")
	public ModelAndView resumo() {
		ModelAndView view = new ModelAndView("tr/pesquisarProjetos");
		view.addObject("termos", this.termoReferenciaRepository.findAll());
		
		return view;

	}
	
	@RequestMapping("/entrarTelaAvalicao/{id}")
	public ModelAndView entrarTelaAddOcorrencia(@PathVariable("id") Integer id) {
		ModelAndView view = new ModelAndView("tr/avaliacaoTR");
		TermoRereferencia tr = termoReferenciaRepository.findById(id).get();
		
		AvaliacaoDOD avaliacao = new AvaliacaoDOD();
		avaliacao.setTermo(tr);
		avaliacao.setObjetivoEstrategicos(tr.getObjetivos());
		avaliacao.setNecessidades(tr.getNecessidades());
		avaliacao.avaliarConsitenciaTexto(tr.getObjetivos());
		avaliacao.avaliarConsitenciaTextoNecessidade(tr.getNecessidades());
		view.addObject("objetivos",avaliacao.getObjetivoEstrategicos());
		view.addObject("necessidades", avaliacao.getNecessidades());
		view.addObject("termo", tr);
		
		return view;

	}
	
	@RequestMapping("/view/{id}")
	public ModelAndView buscarPorId(@PathVariable("id") Integer id) {
		TermoRereferencia tr = this.termoReferenciaRepository.findById(id).get();
		
		ModelAndView view = new ModelAndView("tr/viewTR");
		view.addObject("termo", tr);
		view.addObject("objetivos", tr.getObjetivos());
		view.addObject("necessidades", tr.getNecessidades());
		
		return view;

	}
	
	
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid TermoRereferencia termo, BindingResult result, Model model,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(termo);
		}

		termo.adicionarObjetivos(this.objetivos.getItens());
		termo.adicionarNecessidades(this.necessidades.getItens());
		termo.setSituacao(Situacao.NOVO.getDescricao());
		termoReferenciaRepository.save(termo);
		attributes.addFlashAttribute("message", "Documento Cadastrado com sucesso!");
		return new ModelAndView("redirect:/projeto/novo");
	}
	
	
	
	@GetMapping("/addObjetivo/{nome}")
	public ModelAndView adicionarObjetivo(@PathVariable("nome") String nome) {
		this.objetivos.adicionarObjetivo(nome);
		System.out.println("Adicionando objetivos no controller.....");
		ModelAndView view = new ModelAndView("tr/TabelaObjetivo");
		view.addObject("itens", this.objetivos.getItens());
		return view;

	}
	
	@GetMapping("/addNecessidade/{nome}")
	public ModelAndView adicionarNecessidade(@PathVariable("nome") String nome) {
		this.necessidades.adicionarNecessidade(nome);
		ModelAndView view = new ModelAndView("tr/TabelaNecessidade");
		view.addObject("itens", this.necessidades.getItens());
		return view;

	}
	
	
	

}
