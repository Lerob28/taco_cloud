package tacos.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;

@Data
@Table
public class Taco {

    @Id
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="name must be at least 5 characters long ! ")
    private String name;

    @NotNull
    @Size(min=1, message="you must choose at least 1 ingredient !")
    private List<IngredientRef> ingredientList;
}
