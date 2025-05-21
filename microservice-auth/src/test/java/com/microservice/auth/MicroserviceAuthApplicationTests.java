package com.microservice.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.auth.services.IAuthService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
class MicroserviceAuthApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IAuthService authService;
    
    @Autowired
    private ObjectMapper objectMapper;  
    @Test
    void contextLoads() {
        // Esta prueba verifica que el contexto de la aplicación se cargue sin errores
    }

    
    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"usuario@example.com\", \"password\": \"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())  // Verifica que el accessToken esté presente
                .andExpect(jsonPath("$.refreshToken").exists());  // Verifica que el refreshToken esté presente
    }

  
    @Test
    public void testActivateAccount() throws Exception {
        mockMvc.perform(post("/api/auth/activate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"usuario@example.com\", \"dni\": \"12345678\", \"password\": \"password123\", \"passwordConfirmation\": \"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("usuario@example.com"));
    }


    @Test
    public void testChangePassword() throws Exception {
        String token = "your_valid_token_here"; // Suponiendo que obtuviste un token válido del login

        mockMvc.perform(post("/api/auth/change-password")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"currentPassword\": \"oldpassword123\", \"newPassword\": \"newpassword123\"}"))
                .andExpect(status().isOk());
    }


    @Test
    public void testRefreshToken() throws Exception {
        mockMvc.perform(post("/api/auth/refresh-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"refreshToken\": \"your_refresh_token_here\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())  // Verifica que el accessToken esté presente
                .andExpect(jsonPath("$.refreshToken").exists());  // Verifica que el refreshToken esté presente
    }
}

