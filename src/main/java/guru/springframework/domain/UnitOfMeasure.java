package guru.springframework.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@Document
public class UnitOfMeasure {

    @Id
    private String id;
    private String description;

    public UnitOfMeasure(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public UnitOfMeasure() {
        this.id = "";
        this.description = "";
    }
}
