package br.com.cin.sitcon.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cin.sitcon.model.util.LeitorPac;
import br.com.cin.sitcon.repository.PacRepository;

@Controller
@RequestMapping("/pac")
public class PACController {
	
	static String CADASTRO_PAC= "pac/cadastroPAC";
	private final String UPLOAD_DIR = "./upload/";
	
	@Autowired
	private PacRepository pacRepository;
	@Autowired
	private LeitorPac leitorPacService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(CADASTRO_PAC);
		
		System.out.println(pacRepository.findAll().size());
		return view;
	}
	
	@RequestMapping("/view")
	public ModelAndView resumo() {
		ModelAndView view = new ModelAndView("pac/resumo");
		view.addObject("itenspac",this.pacRepository.findAll());
		return view;
		
	}
	
	@PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("error", "Não foi selecionado nenhum arquivo válido!.");
            return "redirect:/pac/novo";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        leitorPacService.carregar(new File(file.getName()));

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            
            leitorPacService.carregar(new File(UPLOAD_DIR.concat("\\"+fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "Dados carregados com sucesso ");
        System.out.println("Aqui....");
        return "redirect:/pac/novo";
    }

}
