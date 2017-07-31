package br.com.burguer.test.repositories;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.burguer.test.configuration.RepositoryConfiguration;
import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class })
public class ConstrutorsTest {

	@Test
	public void testeHamburguerConstructor() {
		
		Ingredient ingredient = new Ingredient();
    	ingredient.setDescription("Alface");
    	ingredient.setPrice(new BigDecimal("0.40"));
    	
    	Set<Ingredient> ingredients = new HashSet<Ingredient>();
    	ingredients.add(ingredient);
    	
		Hamburguer hamburguer = new Hamburguer("X-Bacon",ingredients, 1, new BigDecimal("4.00"));
		
		assertNotNull(hamburguer);

	}

}
