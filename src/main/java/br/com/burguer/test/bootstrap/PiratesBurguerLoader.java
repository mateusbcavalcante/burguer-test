package br.com.burguer.test.bootstrap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.repositories.HamburguerRepository;
import br.com.burguer.test.repositories.IngredientRepository;

@Component
public class PiratesBurguerLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private HamburguerRepository hamburguerRepository;

	private Logger log = Logger.getLogger(PiratesBurguerLoader.class);

	private static final BigDecimal ALFACE_DEFAULT_VALUE = new BigDecimal("0.40");
	private static final BigDecimal QUEIJO_DEFAULT_VALUE = new BigDecimal("1.50");
	private static final BigDecimal OVO_DEFAULT_VALUE = new BigDecimal("0.80");
	private static final BigDecimal BACON_DEFAULT_VALUE = new BigDecimal("2.00");
	private static final BigDecimal HAMBURGUER_DEFAULT_VALUE = new BigDecimal("3.00");

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// Create Default Ingredients
		Ingredient alface = new Ingredient();
		alface.setDescription("Alface");
		alface.setPrice(ALFACE_DEFAULT_VALUE);
		alface.setDefaultIngredient(true);
		Ingredient bacon = new Ingredient();
		bacon.setDescription("Bacon");
		bacon.setPrice(BACON_DEFAULT_VALUE);
		bacon.setDefaultIngredient(true);
		Ingredient hamburguerCarne = new Ingredient();
		hamburguerCarne.setDescription("Hambúrguer de carne");
		hamburguerCarne.setPrice(HAMBURGUER_DEFAULT_VALUE);
		hamburguerCarne.setDefaultIngredient(true);
		Ingredient ovo = new Ingredient();
		ovo.setDescription("Ovo");
		ovo.setPrice(OVO_DEFAULT_VALUE);
		ovo.setDefaultIngredient(true);
		Ingredient queijo = new Ingredient();
		queijo.setDescription("Queijo");
		queijo.setPrice(QUEIJO_DEFAULT_VALUE);
		queijo.setDefaultIngredient(true);

		ingredientRepository.save(alface);
		ingredientRepository.save(bacon);
		ingredientRepository.save(hamburguerCarne);
		ingredientRepository.save(ovo);
		ingredientRepository.save(queijo);

		// Create Default Hamburgueres
		Hamburguer xbacon = new Hamburguer();
		xbacon.setDescription("X-Bacon");

		Hamburguer xburguer = new Hamburguer();
		xburguer.setDescription("X-Burguer");
		

		Hamburguer xegg = new Hamburguer();
		xegg.setDescription("X-Egg");
		

		Hamburguer xeggBacon = new Hamburguer();
		xeggBacon.setDescription("X-Egg-Bacon");
		

		hamburguerRepository.save(xbacon);
		hamburguerRepository.save(xburguer);
		hamburguerRepository.save(xegg);
		hamburguerRepository.save(xeggBacon);

		xbacon.getIngredients().add(bacon);
		xbacon.getIngredients().add(hamburguerCarne);
		xbacon.getIngredients().add(queijo);
		xbacon.getIngredients().forEach((ingredient) -> {

			createHamburguerIngredient(xbacon, ingredient);

		});

		xburguer.getIngredients().add(hamburguerCarne);
		xburguer.getIngredients().add(queijo);
		xburguer.getIngredients().clear();
		xburguer.getIngredients().forEach((ingredient) -> {

			createHamburguerIngredient(xburguer, ingredient);

		});

		
		xegg.getIngredients().add(hamburguerCarne);
		xegg.getIngredients().add(queijo);
		xegg.getIngredients().add(ovo);
		xegg.getIngredients().forEach((ingredient) -> {

			createHamburguerIngredient(xegg, ingredient);

		});

		xeggBacon.getIngredients().add(hamburguerCarne);
		xeggBacon.getIngredients().add(queijo);
		xeggBacon.getIngredients().add(ovo);
		xeggBacon.getIngredients().add(bacon);
		xeggBacon.getIngredients().forEach((ingredient) -> {

			createHamburguerIngredient(xeggBacon, ingredient);

		});

	}

	private void createHamburguerIngredient(Hamburguer xbacon, Ingredient ingredient) {
		Ingredient in = new Ingredient();
		in.setDescription(ingredient.getDescription());
		in.setPrice(ingredient.getPrice());
		in.setHamburguer(xbacon);
		in.setDefaultIngredient(false);
		ingredientRepository.save(in);
	}
}
