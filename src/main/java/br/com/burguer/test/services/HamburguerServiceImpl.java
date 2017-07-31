package br.com.burguer.test.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.repositories.HamburguerRepository;
import br.com.burguer.test.repositories.IngredientRepository;

@Service
public class HamburguerServiceImpl implements HamburguerService {

	@Autowired
	private HamburguerRepository hamburguerRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@Override
	public Iterable<Hamburguer> listAllHamburguers() {
		return hamburguerRepository.findAll();
	}

	@Override
	public Hamburguer findById(Integer id) {
		return hamburguerRepository.findOne(id);
	}

	@Override
	public Hamburguer addIngredient(Integer hamburguerId, Integer ingredientId) {
		Ingredient ingredient = ingredientRepository.findOne(ingredientId);
		Hamburguer hamburguer = hamburguerRepository.findOne(hamburguerId);

		Ingredient newIng = new Ingredient(ingredient);
		newIng.setHamburguer(hamburguer);

		newIng = ingredientRepository.save(newIng);

		return hamburguer;
	}

	@Override
	public void removeIngredient(Integer hamburguerId, Integer ingredientId) {
		Ingredient ingredient = ingredientRepository.findOne(ingredientId);
		Hamburguer hamburguer = hamburguerRepository.findOne(hamburguerId);
		Iterable<Ingredient> hamburguerIngredients = ingredientRepository.findByHamburguer(hamburguer);
		
		Set<Ingredient> ingredientes = new HashSet<Ingredient>();
		
		hamburguerIngredients.forEach((selectedIngridient) -> {
			if (selectedIngridient.getDescription().equals(ingredient.getDescription())) {
				ingredientes.add(selectedIngridient);
			}
		});
		
		if(ingredientes.iterator().hasNext()) {
			Ingredient ingredientToRemove = ingredientes.iterator().next();
			ingredientRepository.delete(ingredientToRemove);
		}
	}

}
