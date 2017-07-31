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
public class IngredientController {
	
	@Autowired
	private HamburguerService hamburguerService;

	@Autowired
	private IngredientService ingredientService;
	
	private static final String RESULTS_FRAGMENT = "ingredientEdit :: resultsList";
	
	
	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET, produces = "application/json")
	public String listHamburguerIngredients2(@PathVariable Integer id, Model model) {
		Iterable<Ingredient> defaultIngredients = ingredientService.listDefaultAllIngredients();
		model.addAttribute("hamburguerIngredients", defaultIngredients);
		Hamburguer hamburguer = hamburguerService.findById(id);
		model.addAttribute("hamburguerSelected", hamburguer);
		return RESULTS_FRAGMENT;
	}

}
