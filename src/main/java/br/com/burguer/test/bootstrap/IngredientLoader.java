package br.com.burguer.test.bootstrap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.burguer.test.domain.Ingredient;
import br.com.burguer.test.repositories.IngredientRepository;

@Component
public class IngredientLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private IngredientRepository ingredientRepository;

    private Logger log = Logger.getLogger(IngredientLoader.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	Ingredient alface = new Ingredient();
    	alface.setDescription("Alface");
    	alface.setPrice(new BigDecimal("0.40"));
    	
    	
    	Ingredient bacon = new Ingredient();
    	bacon.setDescription("Bacon");
    	bacon.setPrice(new BigDecimal("2.00"));
    	
    	Ingredient hamburguerCarne = new Ingredient();
    	hamburguerCarne.setDescription("Hambúrguer de carne");
    	hamburguerCarne.setPrice(new BigDecimal("3.00"));
    	
    	Ingredient ovo = new Ingredient();
    	ovo.setDescription("Ovo");
    	ovo.setPrice(new BigDecimal("0.80"));
    	
    	Ingredient queijo = new Ingredient();
    	queijo.setDescription("Queijo");
    	queijo.setPrice(new BigDecimal("1.50"));
    	
    	ingredientRepository.save(alface);
    	ingredientRepository.save(bacon);
    	ingredientRepository.save(hamburguerCarne);
    	ingredientRepository.save(ovo);
    	ingredientRepository.save(queijo);

    }
}
