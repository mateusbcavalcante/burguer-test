package br.com.burguer.test.services;

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

}
