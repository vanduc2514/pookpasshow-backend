package com.codegym.pookpasshow.api;

import com.codegym.pookpasshow.services.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class TodoAPITest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TodoService todoService;
}
