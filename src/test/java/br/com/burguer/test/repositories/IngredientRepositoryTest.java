package br.com.burguer.test.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.burguer.test.configuration.RepositoryConfiguration;
import br.com.burguer.test.domain.Ingredient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class IngredientRepositoryTest {

	@Autowired
    private IngredientRepository ingredientRepository;
   

    @Test
    public void testSaveProduct(){
        //setup ingredient
    	Ingredient ingredient = new Ingredient();
    	ingredient.setDescription("Alface");
    	ingredient.setPrice(new BigDecimal("0.40"));
    	

        //save ingredient, verify has ID value after save
        assertNull(ingredient.getId()); //null before save
        ingredientRepository.save(ingredient);
        assertNotNull(ingredient.getId()); //not null after save
        //fetch from DB
        Ingredient fetchedIngredient = ingredientRepository.findOne(ingredient.getId());

        //should not be null
        assertNotNull(fetchedIngredient);

        //should equal
        assertEquals(ingredient.getId(), fetchedIngredient.getId());
        assertEquals(ingredient.getDescription(), fetchedIngredient.getDescription());

        //update price and save
        fetchedIngredient.setPrice(new BigDecimal("0.60"));
        ingredientRepository.save(fetchedIngredient);

        //get from DB, should be updated
        Ingredient fetchedUpdatedIngredient = ingredientRepository.findOne(fetchedIngredient.getId());
        assertEquals(fetchedIngredient.getPrice(), fetchedUpdatedIngredient.getPrice());

        //verify count of products in DB
        long ingredientCount = ingredientRepository.count();
        assertEquals(ingredientCount, 1);

        //get all products, list should only have one
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        int count = 0;

        for(Ingredient p : ingredients){
            count++;
        }

        assertEquals(count, 1);
    }
}
