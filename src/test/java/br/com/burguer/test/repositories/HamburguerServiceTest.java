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
import br.com.burguer.test.services.HamburguerServiceImpl;
import br.com.burguer.test.services.IngredientServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class })
public class HamburguerServiceTest {

	
	@Autowired
	private HamburguerRepository hamburguerRepositoryReal;

	@Mock
	private IngredientRepository ingredientRepository;

	@Mock
	private HamburguerRepository hamburguerRepository;

	@InjectMocks
	private HamburguerServiceImpl hamburguerServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testLoadAllHamburguers() {
		Iterable<Hamburguer> hamburguers = hamburguerRepositoryReal.findAll();
		Mockito.when(hamburguerRepository.findAll()).thenReturn(hamburguers);
		assertNotNull(hamburguerServiceImpl.listAllHamburguers());
	}
	
	
	@Test
	public void testLoadFindOneHamburguers() {
		Hamburguer hamburguers = new Hamburguer();
		Mockito.when(hamburguerRepository.findOne(1)).thenReturn(hamburguers);
		assertNotNull(hamburguerServiceImpl.findById(1));
	}
	
	
	@Test
	public void addIngredient() {
		Hamburguer hamburguers = new Hamburguer();
		Ingredient ingredient = new Ingredient();
		Mockito.when(hamburguerRepository.findOne(1)).thenReturn(hamburguers);
		Mockito.when(ingredientRepository.findOne(1)).thenReturn(ingredient);
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(null);
		
		assertNotNull(hamburguerServiceImpl.addIngredient(1, 1));
	}
	
	@Test
	public void removeIngredient() {
		Hamburguer hamburguer = new Hamburguer();
		Ingredient ingredient = new Ingredient();
		ingredient.setDescription("Queijo");
		Set<Ingredient> setIngr = new HashSet<>();
		setIngr.add(ingredient);
		Mockito.when(hamburguerRepository.findOne(1)).thenReturn(hamburguer);
		Mockito.when(ingredientRepository.findOne(1)).thenReturn(ingredient);
		Mockito.when(ingredientRepository.save(ingredient)).thenReturn(null);
		Mockito.when(ingredientRepository.findByHamburguer(hamburguer)).thenReturn(setIngr);
		
		
		hamburguerServiceImpl.removeIngredient(1, 1);
	}
}
