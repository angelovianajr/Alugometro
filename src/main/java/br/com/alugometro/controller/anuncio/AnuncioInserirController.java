package br.com.alugometro.controller.anuncio;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alugometro.dto.AnuncioDTO;
import br.com.alugometro.service.AnuncioService;

@Controller
@RequestMapping("/anuncio")
public class AnuncioInserirController {

	private AnuncioService anuncioService;

	@Autowired
	public AnuncioInserirController(AnuncioService anuncioService) {
		this.anuncioService = anuncioService;
	}
	
	@RequestMapping(path = "/inserir" , method = RequestMethod.GET)
	public ModelAndView inserir() {	
		return new ModelAndView("anuncio/inserir", "anuncio", new AnuncioDTO());
	}
	
	@RequestMapping(path = "/inserir", method = RequestMethod.POST)
	public ModelAndView inserir(@Valid @ModelAttribute AnuncioDTO anuncioDTO,
								BindingResult result,
								final RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			return new ModelAndView("cliente/inserir");
		}
		
		redirectAttributes.addFlashAttribute("mensagem", "Anuncio feito com sucesso");
		anuncioService.inserir(anuncioDTO);
		return new ModelAndView("redirect:/");
	}
	
}
