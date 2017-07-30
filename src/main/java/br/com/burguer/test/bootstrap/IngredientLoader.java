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
public class IngredientLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private IngredientRepository ingredientRepository;	

	@Autowired
    private HamburguerRepository hamburguerRepository;

    private Logger log = Logger.getLogger(IngredientLoader.class);
    
    private static final BigDecimal ALFACE_DEFAULT_VALUE = new BigDecimal("0.40");
    private static final BigDecimal QUEIJO_DEFAULT_VALUE = new BigDecimal("1.50");    
    private static final BigDecimal OVO_DEFAULT_VALUE = new BigDecimal("0.80");
    private static final BigDecimal BACON_DEFAULT_VALUE =  new BigDecimal("2.00");
    private static final BigDecimal HAMBURGUER_DEFAULT_VALUE =  new BigDecimal("3.00");


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	
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
    	
    	
    	
    	

    }
}
