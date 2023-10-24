package tacos.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import tacos.model.Ingredient;
import tacos.model.Ingredient.INGREDIENT_TYPE;
import tacos.model.Taco;
import tacos.model.TacoOrder;

import tacos.repository.jdbc_template.IngredientRepository;


import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        INGREDIENT_TYPE[] types = Ingredient.INGREDIENT_TYPE.values();

        for(INGREDIENT_TYPE type : types) {
            if ( !model.containsAttribute(type.toString().toLowerCase().trim()) ) {
                model.addAttribute(type.toString().toLowerCase().trim(), FilterByType(ingredients, type));
            }
        }

    }


    @ModelAttribute(name="tacoOrder")
    public TacoOrder order() {
        log.info("adding tacoOrder object to Model Attribute ...");
        return new TacoOrder();
    }


    @ModelAttribute(name="taco")
    public Taco taco() {
        log.info("adding taco object to Model Attribute ...");
        return new Taco();
    }


    @GetMapping
    public String showDesignForm(Model model) {
        log.info("calling design taco view...");
        System.out.println(model.getAttribute("taco"));
        System.out.println(model.getAttribute("tacoOrder"));
        System.out.println(model.getAttribute("veggies"));
        System.out.println(model.getAttribute("cheese"));
        System.out.println(model.getAttribute("protein"));
        System.out.println(model.getAttribute("wrap"));
        System.out.println(model.getAttribute("sauce"));
        return "design";
    }

    @PostMapping
    public String processTaco (@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors())
            return "design";
        tacoOrder.addTaco(taco);
        log.info("processing taco {} : ", taco);
        return "redirect:/orders/current";
    }


    private Iterable<Ingredient> FilterByType(Iterable<Ingredient> ingredients, INGREDIENT_TYPE type) {
        return StreamSupport
            .stream(ingredients.spliterator(), false)
            .filter(ingredient -> ingredient.getType().equals(type))
            .collect(Collectors.toList());
    }

}
