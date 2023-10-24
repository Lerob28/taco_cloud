package tacos.helper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.model.Ingredient;
import tacos.model.Ingredient.INGREDIENT_TYPE;

import java.util.HashMap;
import java.util.Map;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();
    public IngredientByIdConverter() {
        ingredientMap.put("FLTO", Ingredient.builder().id("FLTO").name("Flour Tortilla").type(INGREDIENT_TYPE.WRAP).build());
        ingredientMap.put("COTO", Ingredient.builder().id("COTO").name("Corn Tortilla").type(INGREDIENT_TYPE.WRAP).build());
        ingredientMap.put("GRBF", Ingredient.builder().id("GRBF").name("Ground Beef").type(INGREDIENT_TYPE.PROTEIN).build());
        ingredientMap.put("CARN", Ingredient.builder().id("CARN").name("Carnitas").type(INGREDIENT_TYPE.PROTEIN).build());
        ingredientMap.put("TMTO", Ingredient.builder().id("TMTO").name("Diced Tomatoes").type(INGREDIENT_TYPE.VEGGIES).build());
        ingredientMap.put("LETC", Ingredient.builder().id("LETC").name("Lettuce").type(INGREDIENT_TYPE.VEGGIES).build());
        ingredientMap.put("CHED", Ingredient.builder().id("CHED").name("Cheddar").type(INGREDIENT_TYPE.CHEESE).build());
        ingredientMap.put("JACK", Ingredient.builder().id("JACK").name("Monterrey Jack").type(INGREDIENT_TYPE.CHEESE).build());
        ingredientMap.put("SLSA", Ingredient.builder().id("SLSA").name("Salsa").type(INGREDIENT_TYPE.SAUCE).build());
        ingredientMap.put("SRCR", Ingredient.builder().id("SRCR").name("Sour Cream").type(INGREDIENT_TYPE.SAUCE).build());
    }
    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
