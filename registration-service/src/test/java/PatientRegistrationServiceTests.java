import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class PatientRegistrationServiceTests {

  @Container
  static MySQLContainer mySQLContainer = new MySQLContainer("mySql:latest");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
    dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
  }

  @Test
  void shouldRegisterPatient(){

  }
}
