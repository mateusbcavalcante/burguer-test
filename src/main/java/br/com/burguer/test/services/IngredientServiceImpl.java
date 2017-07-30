package br.com.burguer.test.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.repositories.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Override
	public Iterable<Ingredient> listAllIngredients() {
		return ingredientRepository.findAll();
	}
	
	@Override
	public Set<Ingredient> listDefaultAllIngredients() {
		ArrayList<Ingredient> ing = (ArrayList<Ingredient>) StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
		
		Set<Ingredient> ingredientes = new HashSet<Ingredient>();
		ing.forEach((ingredient) -> {
			if (ingredient.isDefaultIngredient()) {
				ingredientes.add(ingredient);
			}
		});
		
		return ingredientes;
	}
	
	

}
