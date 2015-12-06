package br.com.alugometro.controller.anuncio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.alugometro.dto.AnuncioFotoDTO;
import br.com.alugometro.service.AnuncioFotoService;
import br.com.alugometro.service.AnuncioService;
import br.com.alugometro.service.CidadeService;
import br.com.alugometro.service.TipoAcomodacaoService;
import br.com.alugometro.service.TipoImovelService;

@Controller
@RequestMapping("/anuncio")
public class AnuncioExibirController extends AbstractAnuncioController {

	private AnuncioFotoService anuncioFotoService;
	
	@Autowired
	public AnuncioExibirController(
			AnuncioService anuncioService,
			AnuncioFotoService anuncioFotoService,
			TipoImovelService tipoImovelService,
			TipoAcomodacaoService tipoAcomodacaoService, 
			CidadeService cidadeService) {
		
		super(anuncioService, tipoImovelService, tipoAcomodacaoService, cidadeService);
		this.anuncioFotoService = anuncioFotoService; 
	}
	
	@RequestMapping(path = "/{idAnuncio}",method=RequestMethod.GET)
	public ModelAndView exibir(@PathVariable("idAnuncio") Long idAnuncio) {
		
		ModelAndView model = new ModelAndView("anuncio/exibir", "anuncio", anuncioService.buscarPorID(idAnuncio));
		model.addObject("anuncioFotos", listaFotos(idAnuncio));
		return model;
	}
	
	private List<AnuncioFotoDTO> listaFotos(Long idAnuncio){
		return anuncioFotoService.listarPorIdAnuncio(idAnuncio);
	}
	
}