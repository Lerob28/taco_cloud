package tacos.repository.jdbc_template;

import tacos.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
