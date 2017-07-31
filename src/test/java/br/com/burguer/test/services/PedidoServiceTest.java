package br.com.burguer.test.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.burguer.test.configuration.RepositoryConfiguration;
import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.exception.PirateBurguerException;
import br.com.burguer.test.repositories.HamburguerRepository;
import br.com.burguer.test.repositories.IngredientRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class })
public class PedidoServiceTest {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private HamburguerRepository hamburguerRepository;

	@Bean
	public PedidoService pedidoService() {
		return new PedidoServiceImpl();
	}

	private static final BigDecimal ALFACE_DEFAULT_VALUE = new BigDecimal("0.40");
	private static final BigDecimal QUEIJO_DEFAULT_VALUE = new BigDecimal("1.50");
	private static final BigDecimal OVO_DEFAULT_VALUE = new BigDecimal("0.80");
	private static final BigDecimal BACON_DEFAULT_VALUE = new BigDecimal("2.00");
	private static final BigDecimal HAMBURGUER_DEFAULT_VALUE = new BigDecimal("3.00");

	@Test
	public void testPriceFinal() {

		// Create Default Ingredients
		Ingredient alface = new Ingredient();
		alface.setDescription("Alface");
		alface.setPrice(ALFACE_DEFAULT_VALUE);
		Ingredient bacon = new Ingredient();
		bacon.setDescription("Bacon");
		bacon.setPrice(BACON_DEFAULT_VALUE);
		Ingredient hamburguerCarne = new Ingredient();
		hamburguerCarne.setDescription("Hambúrguer de carne");
		hamburguerCarne.setPrice(HAMBURGUER_DEFAULT_VALUE);
		Ingredient ovo = new Ingredient();
		ovo.setDescription("Ovo");
		ovo.setPrice(OVO_DEFAULT_VALUE);
		Ingredient queijo = new Ingredient();
		queijo.setDescription("Queijo");
		queijo.setPrice(QUEIJO_DEFAULT_VALUE);

		ingredientRepository.save(alface);
		ingredientRepository.save(bacon);
		ingredientRepository.save(hamburguerCarne);
		ingredientRepository.save(ovo);
		ingredientRepository.save(queijo);

		// Create Default Hamburgueres
		Hamburguer xbacon = new Hamburguer();
		xbacon.setDescription("X-Bacon");
		xbacon.getIngredients().add(bacon);
		xbacon.getIngredients().add(hamburguerCarne);
		xbacon.getIngredients().add(queijo);

		Hamburguer xburguer = new Hamburguer();
		xburguer.setDescription("X-Burguer");
		xburguer.getIngredients().add(hamburguerCarne);
		xburguer.getIngredients().add(queijo);

		Hamburguer xegg = new Hamburguer();
		xegg.setDescription("X-Egg");
		xegg.getIngredients().add(hamburguerCarne);
		xegg.getIngredients().add(queijo);
		xegg.getIngredients().add(ovo);

		Hamburguer xeggBacon = new Hamburguer();
		xeggBacon.setDescription("X-Egg-Bacon");
		xeggBacon.getIngredients().add(hamburguerCarne);
		xeggBacon.getIngredients().add(queijo);
		xeggBacon.getIngredients().add(ovo);
		xeggBacon.getIngredients().add(bacon);

		hamburguerRepository.save(xbacon);
		hamburguerRepository.save(xburguer);
		hamburguerRepository.save(xegg);
		hamburguerRepository.save(xeggBacon);

		BigDecimal xbaconFinalPrice = new BigDecimal(bacon.getPrice().doubleValue()
				+ hamburguerCarne.getPrice().doubleValue() + queijo.getPrice().doubleValue());
		xbaconFinalPrice = xbaconFinalPrice.setScale(2, RoundingMode.HALF_UP);
		assertEquals(pedidoService().calculaPrecoFinal(xbacon), xbaconFinalPrice);

	}

	@Test
	public void testPriceFinalComMuitoQueijo() {

		// Create Default Ingredients
		Ingredient alface = new Ingredient();
		alface.setDescription("Alface");
		alface.setPrice(ALFACE_DEFAULT_VALUE);
		Ingredient bacon = new Ingredient();
		bacon.setDescription("Bacon");
		bacon.setPrice(BACON_DEFAULT_VALUE);
		Ingredient hamburguerCarne = new Ingredient();
		hamburguerCarne.setDescription("Hambúrguer de carne");
		hamburguerCarne.setPrice(HAMBURGUER_DEFAULT_VALUE);
		Ingredient ovo = new Ingredient();
		ovo.setDescription("Ovo");
		ovo.setPrice(OVO_DEFAULT_VALUE);
		Ingredient queijo = new Ingredient();
		queijo.setDescription("Queijo");
		queijo.setPrice(QUEIJO_DEFAULT_VALUE);

		ingredientRepository.save(alface);
		ingredientRepository.save(bacon);
		ingredientRepository.save(hamburguerCarne);
		ingredientRepository.save(ovo);
		ingredientRepository.save(queijo);

		// Create Default Hamburgueres
		Hamburguer xbacon = new Hamburguer();
		xbacon.setDescription("X-Bacon");
		xbacon.getIngredients().add(bacon);
		xbacon.getIngredients().add(bacon);
		xbacon.getIngredients().add(hamburguerCarne);
		xbacon.getIngredients().add(queijo);

		Hamburguer xburguer = new Hamburguer();
		xburguer.setDescription("X-Burguer");
		xburguer.getIngredients().add(hamburguerCarne);
		xburguer.getIngredients().add(queijo);

		Hamburguer xegg = new Hamburguer();
		xegg.setDescription("X-Egg");
		xegg.getIngredients().add(hamburguerCarne);
		xegg.getIngredients().add(queijo);
		xegg.getIngredients().add(ovo);

		Hamburguer xeggBacon = new Hamburguer();
		xeggBacon.setDescription("X-Egg-Bacon");
		xeggBacon.getIngredients().add(hamburguerCarne);
		xeggBacon.getIngredients().add(queijo);
		xeggBacon.getIngredients().add(ovo);
		xeggBacon.getIngredients().add(bacon);

		hamburguerRepository.save(xbacon);
		hamburguerRepository.save(xburguer);
		hamburguerRepository.save(xegg);
		hamburguerRepository.save(xeggBacon);

		Ingredient queijo2 = new Ingredient();
		queijo2.setDescription("Queijo");
		queijo2.setPrice(QUEIJO_DEFAULT_VALUE);

		Ingredient queijo3 = new Ingredient();
		queijo3.setDescription("Queijo");
		queijo3.setPrice(QUEIJO_DEFAULT_VALUE);

		xbacon.getIngredients().add(queijo2);
		xbacon.getIngredients().add(queijo3);

		BigDecimal xbaconFinalPrice = new BigDecimal(bacon.getPrice().doubleValue()
				+ hamburguerCarne.getPrice().doubleValue() + queijo.getPrice().doubleValue()
				+ queijo2.getPrice().doubleValue() + queijo3.getPrice().doubleValue()
				- pedidoService().descontoMuito(xbacon, "Queijo").doubleValue());

		xbaconFinalPrice = xbaconFinalPrice.setScale(2, RoundingMode.HALF_UP);

		assertEquals(pedidoService().calculaPrecoFinal(xbacon), xbaconFinalPrice);

	}

	@Test
	public void testPriceFinalComMuitaCarne() {

		// Create Default Ingredients
		Ingredient alface = new Ingredient();
		alface.setDescription("Alface");
		alface.setPrice(ALFACE_DEFAULT_VALUE);
		Ingredient bacon = new Ingredient();
		bacon.setDescription("Bacon");
		bacon.setPrice(BACON_DEFAULT_VALUE);
		Ingredient hamburguerCarne = new Ingredient();
		hamburguerCarne.setDescription("Hambúrguer de carne");
		hamburguerCarne.setPrice(HAMBURGUER_DEFAULT_VALUE);
		Ingredient ovo = new Ingredient();
		ovo.setDescription("Ovo");
		ovo.setPrice(OVO_DEFAULT_VALUE);
		Ingredient queijo = new Ingredient();
		queijo.setDescription("Queijo");
		queijo.setPrice(QUEIJO_DEFAULT_VALUE);

		ingredientRepository.save(alface);
		ingredientRepository.save(bacon);
		ingredientRepository.save(hamburguerCarne);
		ingredientRepository.save(ovo);
		ingredientRepository.save(queijo);

		// Create Default Hamburgueres
		Hamburguer xbacon = new Hamburguer();
		xbacon.setDescription("X-Bacon");
		xbacon.getIngredients().add(bacon);
		xbacon.getIngredients().add(hamburguerCarne);
		xbacon.getIngredients().add(queijo);

		hamburguerRepository.save(xbacon);

		Ingredient carne2 = new Ingredient();
		carne2.setDescription("Hambúrguer de carne");
		carne2.setPrice(HAMBURGUER_DEFAULT_VALUE);
		pedidoService().adicionaIngrediente(xbacon, carne2);
		
		Ingredient carne3 = new Ingredient();
		carne3.setDescription("Hambúrguer de carne");
		carne3.setPrice(HAMBURGUER_DEFAULT_VALUE);
		pedidoService().adicionaIngrediente(xbacon, carne3);


		BigDecimal xbaconFinalPrice = new BigDecimal(bacon.getPrice().doubleValue()
				+ hamburguerCarne.getPrice().doubleValue() + queijo.getPrice().doubleValue()
				+ carne2.getPrice().doubleValue() + carne3.getPrice().doubleValue()
				- pedidoService().descontoMuito(xbacon, "Hambúrguer de carne").doubleValue());

		xbaconFinalPrice = xbaconFinalPrice.setScale(2, RoundingMode.HALF_UP);

		assertEquals(pedidoService().calculaPrecoFinal(xbacon), xbaconFinalPrice);

	}

	@Test
	public void testPriceFinalDesconto10() {

		// Create Default Ingredients
		Ingredient alface = new Ingredient();
		alface.setDescription("Alface");
		alface.setPrice(ALFACE_DEFAULT_VALUE);
		Ingredient bacon = new Ingredient();
		bacon.setDescription("Bacon");
		bacon.setPrice(BACON_DEFAULT_VALUE);
		Ingredient hamburguerCarne = new Ingredient();
		hamburguerCarne.setDescription("Hambúrguer de carne");
		hamburguerCarne.setPrice(HAMBURGUER_DEFAULT_VALUE);
		Ingredient ovo = new Ingredient();
		ovo.setDescription("Ovo");
		ovo.setPrice(OVO_DEFAULT_VALUE);
		Ingredient queijo = new Ingredient();
		queijo.setDescription("Queijo");
		queijo.setPrice(QUEIJO_DEFAULT_VALUE);

		ingredientRepository.save(alface);
		ingredientRepository.save(bacon);
		ingredientRepository.save(hamburguerCarne);
		ingredientRepository.save(ovo);
		ingredientRepository.save(queijo);

		// Create Default Hamburgueres
		Hamburguer xbacon = new Hamburguer();
		xbacon.setDescription("X-Bacon");
		xbacon.getIngredients().add(alface);
		// xbacon.getIngredients().add(bacon);
		xbacon.getIngredients().add(hamburguerCarne);
		xbacon.getIngredients().add(queijo);

		hamburguerRepository.save(xbacon);

		BigDecimal desconto = new BigDecimal((alface.getPrice().doubleValue() + hamburguerCarne.getPrice().doubleValue()
				+ queijo.getPrice().doubleValue()) * 0.1);
		BigDecimal xbaconFinalPrice = new BigDecimal(alface.getPrice().doubleValue()
				+ hamburguerCarne.getPrice().doubleValue() + queijo.getPrice().doubleValue() - desconto.doubleValue());

		xbaconFinalPrice = xbaconFinalPrice.setScale(2, RoundingMode.HALF_UP);

		assertEquals(pedidoService().calculaPrecoFinal(xbacon), xbaconFinalPrice);

	}

	@Test(expected = PirateBurguerException.class)
	public void testException() {
		pedidoService().calculaPrecoFinal(null);
	}
}
