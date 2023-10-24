package tacos.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Ingredient implements Persistable<String> {

    @Id
    private final String id;

    private final String name;

    private final INGREDIENT_TYPE type;

    @Override
    public boolean isNew() {
        return false;
    }

    public enum INGREDIENT_TYPE {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
