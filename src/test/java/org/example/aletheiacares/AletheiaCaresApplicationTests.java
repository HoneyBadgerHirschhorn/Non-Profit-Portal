package org.example.aletheiacares;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.ANY;

@SpringBootTest
@ActiveProfiles("test")                              // <-- pick up your src/test/resources/application.properties
@AutoConfigureTestDatabase(replace = ANY)

public class AletheiaCaresApplicationTests {

    @Test
    void contextLoads() {
    }

}
