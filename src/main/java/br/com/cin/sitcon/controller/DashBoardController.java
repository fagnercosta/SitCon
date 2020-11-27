package br.com.cin.sitcon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/dashboard")
public class DashBoardController {
	
	
	@GetMapping
	public ModelAndView home() {
		ModelAndView view = new ModelAndView("projeto/dashboard");
		return view;
	}
	
	
}
