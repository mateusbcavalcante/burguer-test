package br.com.burguer.test.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.burguer.test.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

}
