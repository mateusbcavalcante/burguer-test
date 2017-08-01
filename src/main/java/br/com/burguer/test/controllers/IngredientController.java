package br.com.burguer.test.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.services.IngredientService;

@Controller
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	private static final String RESULTS_FRAGMENT = "ingredientEdit :: resultsListIngredients";

	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET, produces = "application/json")
	public String listHamburguerIngredients(@PathVariable Integer id, Model model) {
		Ingredient ingredient = ingredientService.findOne(id);

		model.addAttribute("ingredientSelected", ingredient);
		return RESULTS_FRAGMENT;
	}

//	@RequestMapping(value = "/ingredient/edit/{ingredientId}/{priceValue}", method = RequestMethod.POST, produces = "application/json")
//	public String editIngredientValue(@PathVariable Integer ingredientId,@PathVariable String priceValue, Model model) {
//		
//		ingredientService.editIngredientPrice(ingredientId, priceValue);
//		return RESULTS_FRAGMENT;
//	}
	
	@RequestMapping(value = "/ingredient/edit/", method = RequestMethod.POST)
	public String editIngredientValue(final Ingredient ingredient) {
		ingredientService.editIngredientPrice(ingredient.getId(), ingredient.getPrice().toString());
		return RESULTS_FRAGMENT;
	}

}
