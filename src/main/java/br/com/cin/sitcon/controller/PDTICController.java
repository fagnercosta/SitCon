package br.com.cin.sitcon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pdtic")
public class PDTICController {

	static String CADASTRO_PDTIC = "pdtic/cadastroPDTIC";
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(CADASTRO_PDTIC);
		return view;
	}
}
