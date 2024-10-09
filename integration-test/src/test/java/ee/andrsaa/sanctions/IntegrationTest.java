package ee.andrsaa.sanctions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
@SpringBootTest
@Tag("integration-test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles(profiles = {"test", "in-memory-filestore"})
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public abstract class IntegrationTest {
  @Container
  public static PostgreSqlContainer postgresqlContainer = PostgreSqlContainer.getInstance();
  @Autowired
  protected DataSource ds;
  @Autowired
  protected ApplicationContext applicationContext;

  @Order(1)
  @BeforeEach
  void setupDs() {
    new JdbcTemplate(ds).execute("CREATE EXTENSION IF NOT EXISTS pg_trgm SCHEMA sanctions");
    new JdbcTemplate(ds).execute("SET search_path TO sanctions");
  }
}
