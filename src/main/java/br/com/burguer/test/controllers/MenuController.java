package br.com.burguer.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.burguer.test.services.IngredientService;

@Controller
public class MenuController {

	@Autowired
	private IngredientService ingredientService;

	@RequestMapping(value = "/menu/ingredients", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("ingredients", ingredientService.listAllIngredients());
		System.out.println("Returning ingredients:");
		return "ingredients";
	}

	

}
