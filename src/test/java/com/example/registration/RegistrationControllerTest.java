package com.example.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "registration.minimum-age=21")
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void rejectsRegistrationBelowConfiguredMinimumAge() throws Exception {
        mockMvc.perform(post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email":"person@example.com","age":20}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.minimumAge").value(21))
                .andExpect(jsonPath("$.error").value("Registration requires an age of at least 21."));
    }

    @Test
    void acceptsRegistrationAtConfiguredMinimumAge() throws Exception {
        mockMvc.perform(post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email":"person@example.com","age":21}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("person@example.com"))
                .andExpect(jsonPath("$.age").value(21));
    }

    @Test
    void rejectsInvalidEmail() throws Exception {
        mockMvc.perform(post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email":"not-an-email","age":21}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Email or age is invalid."));
    }
}