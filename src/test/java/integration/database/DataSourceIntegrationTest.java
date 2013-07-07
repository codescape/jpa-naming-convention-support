package integration.database;

import orderentry.ApplicationConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = {ApplicationConfig.class})
public class DataSourceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void notNull() throws Exception {
        assertNotNull(dataSource);
    }

    @Test
    public void select() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM categories");
        assertEquals(0, jdbcTemplate.queryForList("SELECT 1 FROM categories", Integer.class).size());
    }

}
