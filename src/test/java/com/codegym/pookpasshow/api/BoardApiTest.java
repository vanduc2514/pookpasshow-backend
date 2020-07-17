package com.codegym.pookpasshow.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class BoardApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get board from user name")
    void getBoardFromUserName() {
        
    }
}
