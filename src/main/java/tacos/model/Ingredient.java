package tacos.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ingredient {

    private final String id;
    private final String name;
    private final INGREDIENT_TYPE type;

    public enum INGREDIENT_TYPE {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
