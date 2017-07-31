package br.com.burguer.test.repositories;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.burguer.test.configuration.RepositoryConfiguration;
import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.services.IngredientServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class })
public class IngredientServiceTest {

	@Autowired
	private IngredientRepository ingredientRepositoryReal;

	@Mock
	private IngredientRepository ingredientRepository;

	@Mock
	private HamburguerRepository hamburguerRepository;

	@InjectMocks
	private IngredientServiceImpl ingredientServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testLoadAll() {
		Iterable<Ingredient> ingredients = ingredientRepositoryReal.findAll();
		Mockito.when(ingredientRepository.findAll()).thenReturn(ingredients);
		assertNotNull(ingredientServiceImpl.listAllIngredients());
	}

	@Test
	public void testDefaultLoadAll() {
		Iterable<Ingredient> ingredients = ingredientRepositoryReal.findAll();

		ArrayList<Ingredient> ing = (ArrayList<Ingredient>) StreamSupport
				.stream(ingredients.spliterator(), false).collect(Collectors.toList());

		Set<Ingredient> ingredientes = new HashSet<Ingredient>();
		
		ing.forEach((ingredient) -> {
			ingredient.setDefaultIngredient(new Boolean(true));
			ingredientes.add(ingredient);
		});
		
		Mockito.when(ingredientRepository.findAll()).thenReturn(ingredientes);
		assertNotNull(ingredientServiceImpl.listDefaultAllIngredients());
	}
	
	@Test
	public void findIngredientsByHamburguerId() {
		Hamburguer hamburguer = new Hamburguer();

		
		
		Mockito.when(hamburguerRepository.findOne(1)).thenReturn(hamburguer);
		assertNotNull(ingredientServiceImpl.findIngredientsByHamburguerId(1));
	}
	
	@Test
	public void findOne() {
		Ingredient ingredient = new Ingredient();

		
		
		Mockito.when(ingredientRepository.findOne(1)).thenReturn(ingredient);
		assertNotNull(ingredientServiceImpl.findOne(1));
	}
}
