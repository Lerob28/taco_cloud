package tacos.repository.jdbc_template;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.model.Ingredient;
import tacos.repository.jdbc_template.IngredientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateIngredientRepositoryImpl implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateIngredientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query(
            "select id, name, type from Ingredient",
            this::mapRowToIngredient
        );
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> result = jdbcTemplate.query(
        "select id, name, type from Ingredient where id=?",
            this::mapRowToIngredient,
            id
        );
        return result.size() == 0
                ? Optional.empty()
                : Optional.of(result.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
        "insert into Ingredient (id, name, type) values (?,?,?)",
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getType()
        );
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return Ingredient.builder()
                .id(row.getString("id"))
                .name(row.getString("name"))
                .type(Ingredient.INGREDIENT_TYPE.valueOf(row.getString("type")))
                .build();
    }
}