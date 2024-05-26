package dev.khong.toohak;

import dev.khong.toohak.repositories.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminAuthRegisterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    private final String endpoint = "/v1/admin/auth/register";

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldRegisterUser() throws Exception {
        String content = """
                {
                    "email": "email123@gmail.com",
                    "password": "Password123",
                    "nameFirst": "John",
                    "nameLast": "Doe"
                }
                """;

        mockMvc.perform(post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("registering with existing email should return a 400 bad request and an error")
    public void shouldReturnErrorExistingEmail() throws Exception {
        String content = """
                {
                    "email": "email123@gmail.com",
                    "password": "123456",
                    "nameFirst": "test",
                    "nameLast": "user"
                }
                """;
        mockMvc.perform(post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        mockMvc.perform(post("/v1/admin/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest());
    }
}
