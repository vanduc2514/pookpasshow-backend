package com.codegym.pookpasshow.api;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.services.todo.TodoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class TodoAPITest {
    public static final int SAMPLE_TODO_LIST_SIZE = 10;
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    private static List<Todo> todoList;

    @BeforeAll
    public static void setUpTodoList() {
        todoList = new LinkedList<>();
        for (int index = 0; index < SAMPLE_TODO_LIST_SIZE; ++index) {
            Todo todoSample = new Todo();
            todoSample.setId(index);
            todoSample.setTitle("Sample_Title_" + index);
            todoSample.setContent("Sample_Content_" + index);
            todoSample.setCompleted(false);
            todoList.add(todoSample);
        }
    }

    @Test
    @DisplayName("GET Request trả về 1 List Todo không sort có độ dài theo sample list")
    public void getTodoList() throws Exception {
        when(todoService.getAll()).thenReturn(todoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(SAMPLE_TODO_LIST_SIZE))).andDo(print());
    }
}
