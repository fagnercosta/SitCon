package br.com.cin.sitcon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cin.sitcon.repository.DodRepository;

@Controller
@RequestMapping("/demanda")
public class DodController {
	
	@Autowired
	DodRepository dodRepository;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		return new ModelAndView("demanda/cadastro");
	}

	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView view = new ModelAndView("demanda/pesquisar");
		view.addObject(dodRepository.findAll());
		return view;
		
	}
}
