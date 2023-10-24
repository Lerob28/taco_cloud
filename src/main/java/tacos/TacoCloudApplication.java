package tacos;



import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tacos.model.Ingredient;
import tacos.repository.data_jdbc.IngredientRepository;


// @SpringBootApplication  ==  @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class TacoCloudApplication {
	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return args -> {
			repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.INGREDIENT_TYPE.WRAP));
			repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.INGREDIENT_TYPE.WRAP));
			repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.INGREDIENT_TYPE.PROTEIN));
			repo.save(new Ingredient("CARN", "Carnitas", Ingredient.INGREDIENT_TYPE.PROTEIN));
			repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.INGREDIENT_TYPE.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", Ingredient.INGREDIENT_TYPE.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar", Ingredient.INGREDIENT_TYPE.CHEESE));
			repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.INGREDIENT_TYPE.CHEESE));
			repo.save(new Ingredient("SLSA", "Salsa", Ingredient.INGREDIENT_TYPE.SAUCE));
			repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.INGREDIENT_TYPE.SAUCE));
		};
	}

}
