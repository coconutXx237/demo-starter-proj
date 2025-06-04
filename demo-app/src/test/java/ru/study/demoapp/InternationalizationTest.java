package ru.study.demoapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class InternationalizationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLocaleDefault() throws Exception {
        String response = mockMvc.perform(get("/hello-lang"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Response default: " + response);
        assertEquals("Welcome!", response);
    }

    @Test
    void testLocaleRu() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello-lang")
               .header("Accept-Language", "ru");
        String response = mockMvc.perform(requestBuilder)
                .andReturn().getResponse().getContentAsString();
        System.out.println("Response ru: " + response);
        assertEquals("Привет!", response);
    }

    @Test
    void testLocaleDe() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello-lang")
                .header("Accept-Language", "de");
        String response = mockMvc.perform(requestBuilder)
                .andReturn().getResponse().getContentAsString();
        System.out.println("Response de: " + response);
        assertEquals("Wilcommen!", response);
    }
}
