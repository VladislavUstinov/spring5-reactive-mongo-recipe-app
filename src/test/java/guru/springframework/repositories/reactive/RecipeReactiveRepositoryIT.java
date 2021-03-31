package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() throws Exception {
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void saveAll () {
        Recipe recipe0 = new Recipe();
        recipe0.setDescription("recipe0");

        Recipe recipe1 = new Recipe();
        recipe1.setDescription("recipe1");

        recipeReactiveRepository.save(recipe0).block();
        recipeReactiveRepository.save(recipe1).block();

        assertEquals ( recipeReactiveRepository.count().block().toString(), "2");
    }

    @Test
    public void findByDescription () {
        Recipe recipe0 = new Recipe();
        recipe0.setDescription("recipe0");

        Recipe recipe1 = new Recipe();
        recipe1.setDescription("recipe1");

        recipeReactiveRepository.save(recipe0).block();
        recipeReactiveRepository.save(recipe1).block();

        assertEquals ( recipeReactiveRepository.count().block().toString(), "2");

        Mono<Recipe> res = recipeReactiveRepository.findByDescription("recipe0");

        assertEquals(res.block().getDescription(), "recipe0");
    }

}

