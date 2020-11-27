package br.com.cin.sitcon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ControllerPadrao {
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView view = new ModelAndView("projeto/dashboard");
		return view;
	}
}
