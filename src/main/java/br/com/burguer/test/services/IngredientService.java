package br.com.burguer.test.services;

import br.com.burguer.test.domain.Ingredient;

public interface IngredientService {
	
	
	Iterable<Ingredient> listAllIngredients();

}
