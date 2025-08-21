package id.andriawan.newsapidemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${news.api.key}")
    private String apiKey;

    @Test
    void corsConfiguration_shouldAllowConfiguredOrigin() throws Exception {
        mockMvc.perform(options("/api/v1/any-endpoint")
                        .header("Origin", "http://localhost:8080")
                        .header("X-Api-Key", apiKey)
                        .header("Access-Control-Request-Method", "GET"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:8080"));
    }

    @Test
    void corsConfiguration_shouldRejectUnallowedOrigin() throws Exception {
        mockMvc.perform(options("/api/v1/any-endpoint")
                        .header("X-Api-Key", apiKey)
                        .header("Origin", "https://unauthorized-domain.com")
                        .header("Access-Control-Request-Method", "GET"))
                .andExpect(status().isForbidden())
                .andExpect(header().doesNotExist("Access-Control-Allow-Origin"));
    }

}
