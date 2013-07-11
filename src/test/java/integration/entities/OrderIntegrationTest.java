package integration.entities;

import integration.BaseSpringContextTests;
import orderentry.entities.Customer;
import orderentry.entities.Order;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class OrderIntegrationTest extends BaseSpringContextTests {

    @Test
    public void shouldSaveOrderWithCustomer() {
        Customer customer = new Customer("username");
        entityManager.persist(customer);

        Order order = new Order(customer);
        entityManager.persist(order);

        assertNotNull(order.getId());
        assertNotNull(customer.getId());
    }

}
