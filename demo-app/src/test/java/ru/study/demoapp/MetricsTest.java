package ru.study.demoapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MetricsTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCustomMetric()  {
        assertEquals(0, getCounterMetricValue());
        sendRequestToIncrementMetric();
        assertEquals(1, getCounterMetricValue());
        sendRequestToIncrementMetric();
        assertEquals(2, getCounterMetricValue());
    }

    private void sendRequestToIncrementMetric() {
        try {
            mockMvc.perform(get("/public/hello-world"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getCounterMetricValue() {
        String response;
        try {
            response = mockMvc.perform(get("/actuator/metrics/custom.my_counter"))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JsonNode root;
        try {
            root = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return root.path("measurements").findValue("value").asInt();
    }

/*  http://localhost:8080/actuator/metrics/custom.my_counter
    {
        "name": "custom.my_counter",
            "measurements": [
        {
            "statistic": "COUNT",
                "value": 0
        }
  ],
        "availableTags": []
    }*/
}
