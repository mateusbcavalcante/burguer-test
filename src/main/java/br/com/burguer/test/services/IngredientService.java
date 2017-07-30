package br.com.burguer.test.services;

import java.util.Set;

import br.com.burguer.test.domain.Ingredient;

public interface IngredientService {
	
	
	Iterable<Ingredient> listAllIngredients();
	
	Set<Ingredient> listDefaultAllIngredients();

}
