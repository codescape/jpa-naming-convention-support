package integration.entities;

import integration.BaseSpringContextTests;
import orderentry.entities.Category;
import orderentry.entities.Item;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertNotNull;

public class ItemIntegrationTest extends BaseSpringContextTests {

    @Test
    public void shouldSaveItemWithCategory() {
        Category category = new Category("name", "description");
        entityManager.persist(category);

        Item item = new Item("name", BigDecimal.ONE, category);
        entityManager.persist(item);
        entityManager.flush();

        assertNotNull(item.getId());
        assertNotNull(category.getId());
    }

}
