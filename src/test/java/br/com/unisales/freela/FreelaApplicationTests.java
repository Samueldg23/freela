package br.com.unisales.freela;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Adicione esta linha
public class FreelaApplicationTests {
    @Test
    void contextLoads() {
    }
}