package br.com.burguer.test.services;

import br.com.burguer.test.domain.Hamburguer;

public interface HamburguerService {
	
	
	Iterable<Hamburguer> listAllHamburguers();
	
	Hamburguer findById(Integer id);
	
	Hamburguer addIngredient(Integer hamburguerId, Integer ingredientId);
	
	void removeIngredient(Integer hamburguerId, Integer ingredientId);

}
