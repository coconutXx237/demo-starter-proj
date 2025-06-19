package ru.study.demoapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    @Test
    void createsUserSuccessfully() throws Exception {
        mockMvc.perform(post("/public/add-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"privet\", \"password\":\"kakdela\"}"))
                .andExpect(status().isOk());
        assertTrue(userDetailsManager.userExists("privet"));
    }

    @Test
    void userRoleTest() throws Exception {
        mockMvc.perform(get("/user/info")
                        .with(httpBasic("vasya", "password")))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, user!"));
    }

    @Test
    void userRoleWrongName() throws Exception {
        mockMvc.perform(get("/user/info")
                        .with(httpBasic("vasya11", "password")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void userRoleWrongPass() throws Exception {
        mockMvc.perform(get("/user/info")
                        .with(httpBasic("vasya", "password11")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void userRoleWrongBothNamePass() throws Exception {
        mockMvc.perform(get("/user/info")
                        .with(httpBasic("vasya11", "password11")))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void adminRoleTest() throws Exception {
        mockMvc.perform(get("/admin/info")
                        .with(httpBasic("petya", "password")))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, admin!"));
    }

    @Test
    void adminRoleWrongName() throws Exception {
        mockMvc.perform(get("/admin/info")
                        .with(httpBasic("petya11", "password")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminRoleWrongPass() throws Exception {
        mockMvc.perform(get("/admin/info")
                        .with(httpBasic("petya", "password11")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminRoleWrongBothNamePass() throws Exception {
        mockMvc.perform(get("/admin/info")
                        .with(httpBasic("petya11", "password11")))
                .andExpect(status().isUnauthorized());
    }
}
