package br.com.cin.sitcon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/projeto")
public class TRController {
	
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView("tr/cadastroTR");
		return view;
		
	}

}
