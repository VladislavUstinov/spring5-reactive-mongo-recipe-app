package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void saveAll () {
        unitOfMeasureReactiveRepository.save(new UnitOfMeasure("1", "Tablespoon")).block();
        unitOfMeasureReactiveRepository.save(new UnitOfMeasure("2", "Piece")).block();

        assertEquals ( unitOfMeasureReactiveRepository.count().block().toString(), "2");
    }
}
