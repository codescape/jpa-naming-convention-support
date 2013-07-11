package integration.entities;

import integration.BaseSpringContextTests;
import orderentry.entities.Category;
import orderentry.entities.Item;
import orderentry.entities.Order;
import orderentry.entities.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertNotNull;

public class OrderItemIntegrationTest extends BaseSpringContextTests {

    @Test
    public void shouldSaveOrderItemWithItem() {
        Category category = new Category("name", "description");
        Item item = new Item("name", BigDecimal.ONE, category);
        OrderItem orderItem = new OrderItem(item, 10);

        entityManager.persist(category);
        entityManager.persist(item);
        entityManager.persist(orderItem);

        assertNotNull(orderItem.getId());
    }

    @Test
    public void shouldSaveOrderItemWithOrder() {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        order.addOrderItem(orderItem);

        entityManager.persist(order);
        entityManager.persist(orderItem);

        assertNotNull(orderItem.getId());
    }

}
