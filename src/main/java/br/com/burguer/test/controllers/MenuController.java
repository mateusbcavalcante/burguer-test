package br.com.burguer.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.services.HamburguerService;
import br.com.burguer.test.services.IngredientService;
import br.com.burguer.test.services.PedidoService;

@Controller
public class MenuController {

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private HamburguerService hamburguerService;
	
	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value = "/menu/cardapio", method = RequestMethod.GET)
	public String listIngredients(Model model) {

		model.addAttribute("ingredients", ingredientService.listDefaultAllIngredients());
				
		Iterable<Hamburguer> hamburguers = hamburguerService.listAllHamburguers();
		
		hamburguers.forEach(hamb -> hamb.setPrice(pedidoService.calculaPrecoFinal(hamb)));
		
		model.addAttribute("burguers", hamburguers);

		return "cardapio";
	}

}
