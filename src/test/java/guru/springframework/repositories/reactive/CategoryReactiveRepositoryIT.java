package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
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
public class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void saveAll () {
        Category category0 = new Category();
        category0.setDescription("category0");

        Category category1 = new Category();
        category1.setDescription("category1");

        categoryReactiveRepository.save(category0).block();
        categoryReactiveRepository.save(category1).block();

        assertEquals ( categoryReactiveRepository.count().block().toString(), "2");
    }

    @Test
    public void findByDescription () {
        Category category0 = new Category();
        category0.setDescription("category0");

        Category category1 = new Category();
        category1.setDescription("category1");

        categoryReactiveRepository.save(category0).block();
        categoryReactiveRepository.save(category1).block();

        assertEquals ( categoryReactiveRepository.count().block().toString(), "2");

        Mono<Category> res = categoryReactiveRepository.findByDescription("category0");

        assertEquals(res.block().getDescription(), "category0");
    }
}
