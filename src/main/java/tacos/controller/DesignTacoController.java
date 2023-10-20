package tacos.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.model.Ingredient;
import tacos.model.Ingredient.INGREDIENT_TYPE;
import tacos.model.Taco;
import tacos.model.TacoOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientToModel(Model model) {

        List<Ingredient> ingredients = Arrays.asList(
            Ingredient.builder().id("TMTO").name("Diced Tomatoes").type(Ingredient.INGREDIENT_TYPE.VEGGIES.name()).build(),
            Ingredient.builder().id("JACK").name("Monterrey Jack").type(Ingredient.INGREDIENT_TYPE.CHEESE.name()).build(),
            Ingredient.builder().id("GRBF").name("Ground Beef").type(Ingredient.INGREDIENT_TYPE.PROTEIN.name()).build(),
            Ingredient.builder().id("FLTO").name("Flour Tortilla").type(Ingredient.INGREDIENT_TYPE.WRAP.name()).build(),
            Ingredient.builder().id("COTO").name("Corn Tortilla").type(Ingredient.INGREDIENT_TYPE.WRAP.name()).build(),
            Ingredient.builder().id("SRCR").name("Sour Cream").type(Ingredient.INGREDIENT_TYPE.SAUCE.name()).build(),
            Ingredient.builder().id("CARN").name("Carnitas").type(Ingredient.INGREDIENT_TYPE.PROTEIN.name()).build(),
            Ingredient.builder().id("LETC").name("Lettuce").type(Ingredient.INGREDIENT_TYPE.VEGGIES.name()).build(),
            Ingredient.builder().id("CHED").name("Cheddar").type(Ingredient.INGREDIENT_TYPE.CHEESE.name()).build(),
            Ingredient.builder().id("SLSA").name("Salsa").type(Ingredient.INGREDIENT_TYPE.SAUCE.name()).build()
        );

        INGREDIENT_TYPE[] types = Ingredient.INGREDIENT_TYPE.values();

        for(INGREDIENT_TYPE type : types) {
            if ( ! model.containsAttribute(type.toString().toLowerCase().trim()) ) {
                model.addAttribute(type.toString().toLowerCase().trim(), FilterByType(ingredients, type));
            }
        }

    }


    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }


    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }


    @GetMapping
    public String showDesignForm() {
        return "design";
    }


    private Iterable<Ingredient> FilterByType(List<Ingredient> ingredients, INGREDIENT_TYPE type) {
        return ingredients.stream()
            .filter(ingredient -> ingredient.getType().equals(type))
            .collect(Collectors.toList());
    }

}
