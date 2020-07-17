package com.codegym.pookpasshow.api;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.services.todo.TodoService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;

@WebMvcTest
public class TodoAPITest {
    public static final int DEMO_LIST_SIZE = 10;
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    private static List<Todo> todoList;

    @BeforeAll
    public static void setUpTodoList() {
        todoList = new LinkedList<>();
        for (int index = 0; index < DEMO_LIST_SIZE; ++index) {
            Todo todoSample = new Todo();
            todoSample.setId(index);
            todoSample.setTitle("Sample_Title_" + index);
            todoSample.setContent("Sample_Content_" + index);
            todoSample.setCompleted(false);
            todoList.add(todoSample);
        }
    }
}
