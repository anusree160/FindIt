
package com.findit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FindItApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("healthy"));
    }

    @Test
    void testAddItem() throws Exception {
        mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Wallet",
                            "description": "Black wallet near library",
                            "type": "lost"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Wallet"))
                .andExpect(jsonPath("$.claimed").value(false));
    }

    @Test
    void testSearchItem() throws Exception {
        mockMvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Phone",
                            "description": "Blue phone",
                            "type": "found"
                        }
                        """));

        mockMvc.perform(get("/items/search")
                .param("name", "Phone"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Phone"));
    }
}
