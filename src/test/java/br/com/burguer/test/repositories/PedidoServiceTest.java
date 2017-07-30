package br.com.burguer.test.repositories;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.burguer.test.configuration.RepositoryConfiguration;
import br.com.burguer.test.domain.Hamburguer;
import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.services.PedidoService;
import br.com.burguer.test.services.PedidoServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
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
    private static final BigDecimal BACON_DEFAULT_VALUE =  new BigDecimal("2.00");
    private static final BigDecimal HAMBURGUER_DEFAULT_VALUE =  new BigDecimal("3.00");
   

    @Test
    public void testPriceFinal(){
    	
    	//Create Default Ingredients
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
    	
    	
    	//Create Default Hamburgueres
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
    	
    	
    	BigDecimal xbaconFinalPrice = new BigDecimal(bacon.getPrice().doubleValue() + hamburguerCarne.getPrice().doubleValue() + queijo.getPrice().doubleValue());
    	
    	assertEquals(pedidoService().calculaPrecoFinal(xbacon), xbaconFinalPrice);
        
    }
    
    
    @Test
    public void testPriceFinalComMuitoQueijo(){
    	
    	//Create Default Ingredients
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
    	
    	
    	//Create Default Hamburgueres
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
    	
    	
    	Ingredient queijo2 = new Ingredient();
    	queijo2.setDescription("Queijo");
    	queijo2.setPrice(QUEIJO_DEFAULT_VALUE);
    	
    	Ingredient queijo3 = new Ingredient();
    	queijo3.setDescription("Queijo");
    	queijo3.setPrice(QUEIJO_DEFAULT_VALUE);
    	
    	xbacon.getIngredients().add(queijo2);
    	xbacon.getIngredients().add(queijo3);
    	
    	BigDecimal xbaconFinalPrice = new BigDecimal(bacon.getPrice().doubleValue() + hamburguerCarne.getPrice().doubleValue() + queijo.getPrice().doubleValue() + queijo.getPrice().doubleValue() + queijo.getPrice().doubleValue());
    	BigDecimal xbaconFinalPriceDiscont = pedidoService().descontoMuito(xbacon, "Queijo");
    	
    	
    	assertEquals(pedidoService().calculaPrecoFinal(xbacon), new BigDecimal(xbaconFinalPrice.doubleValue() - xbaconFinalPriceDiscont.doubleValue()));
        
    }
}
