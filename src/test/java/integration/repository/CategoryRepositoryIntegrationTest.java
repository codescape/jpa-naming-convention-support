package integration.repository;

import integration.BaseSpringContextTests;
import orderentry.entities.Category;
import orderentry.repositories.CategoryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CategoryRepositoryIntegrationTest extends BaseSpringContextTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findAll_noCategories_returnsEmptyList() {
        assertThat(categoryRepository.findAll().size(), is(0));
    }

    @Test
    public void findAll_oneCategory_returnsListWithCategory() {
        Category newCategory = new Category("Name", "Description");
        entityManager.persist(newCategory);
        entityManager.flush();
        List<Category> allCategories = categoryRepository.findAll();
        assertThat(allCategories.size(), is(1));
        assertThat(allCategories.get(0).getName(), is(newCategory.getName()));
        assertThat(allCategories.get(0).getDescription(), is(newCategory.getDescription()));
    }

    @Test
    public void findByName() {
        entityManager.persist(new Category("One", "One"));
        entityManager.persist(new Category("Two", "Two"));
        entityManager.flush();
        assertNotNull(categoryRepository.findByName("One"));
    }

}
