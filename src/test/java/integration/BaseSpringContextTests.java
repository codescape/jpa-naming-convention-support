package integration;

import orderentry.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@ContextConfiguration(classes = {ApplicationConfig.class})
public abstract class BaseSpringContextTests extends AbstractTransactionalJUnit4SpringContextTests {

    protected JdbcTemplate jdbcTemplate;

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected void save(Object entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

}
