package ee.andrsaa.sanctions;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSqlContainer extends PostgreSQLContainer<PostgreSqlContainer> {

  private static final String IMAGE_VERSION = "postgres:17.0";
  private static PostgreSqlContainer container;

  private PostgreSqlContainer() {
    super(IMAGE_VERSION);
  }

  public static PostgreSqlContainer getInstance() {
    if (container == null) {
      container = new PostgreSqlContainer();
    }
    container.withDatabaseName("sanctions");
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("TEST_DB_URL", container.getJdbcUrl());
    System.setProperty("TEST_DB_USERNAME", container.getUsername());
    System.setProperty("TEST_DB_PASSWORD", container.getPassword());
  }

  @Override
  public void stop() {
  }
}
