package br.com.burguer.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.services.HamburguerService;
import br.com.burguer.test.services.IngredientService;

@Controller
public class HamburguerController {

	@Autowired
	private HamburguerService hamburguerService;

	@Autowired
	private IngredientService ingredientService;

	private static final String RESULTS_FRAGMENT = "hamburguerEdit :: resultsList";

	@RequestMapping(value = "/hamburguer/{id}", method = RequestMethod.GET, produces = "application/json")
	public String listHamburguerIngredients2(@PathVariable Integer id, Model model) {
		Iterable<Ingredient> defaultIngredients = ingredientService.listDefaultAllIngredients();
		model.addAttribute("hamburguerIngredients", defaultIngredients);
		Hamburguer hamburguer = hamburguerService.findById(id);
		model.addAttribute("hamburguerSelected", hamburguer);
		return RESULTS_FRAGMENT;
	}

	@RequestMapping(value = "/hamburguer/add/{hamburguerId}/{ingredientId}", method = RequestMethod.POST)
	public String adicionaIngredientes(@PathVariable Integer hamburguerId, @PathVariable Integer ingredientId, Model model) {
		hamburguerService.addIngredient(hamburguerId, ingredientId);
		return "cardapio";
	}
	
	@RequestMapping(value = "/hamburguer/remove/{hamburguerId}/{ingredientId}", method = RequestMethod.POST)
	public String removeIngredientes(@PathVariable Integer hamburguerId, @PathVariable Integer ingredientId, Model model) {
		hamburguerService.removeIngredient(hamburguerId, ingredientId);
		return "cardapio";
	}

}
