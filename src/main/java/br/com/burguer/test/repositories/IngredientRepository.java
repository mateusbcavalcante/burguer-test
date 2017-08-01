package br.com.burguer.test.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
	
	
	
	Set<Ingredient> findByHamburguer(Hamburguer hamburguer);
	
	Set<Ingredient> findByDescription(String description);

}
