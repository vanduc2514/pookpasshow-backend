package com.codegym.pookpasshow.api;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.services.todo.TodoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
@TestPropertySource("classpath:application.properties")
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

    @BeforeEach
    public void setUpService() {
//        Page<Todo> todoPage = new PageImpl<>(todoList);
//        Pageable defaultPageable = PageRequest.of(0, 5, Sort.unsorted());
//        when(todoService.getAll(ArgumentMatchers.any(Pageable.class))).thenReturn(todoPage);
        when(todoService.getAll()).thenReturn(todoList);
        when(todoService.getOne(1)).thenReturn(todoList.get(1));
    }

    private Page<Todo> getPageFromPageable(Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), todoList.size());
        return new PageImpl<>(todoList.subList(start, end), pageable, todoList.size());
    }

    @Test
    @DisplayName("GET Request không có page trả về page mặc định ")
    public void getTodoPage() throws Exception {
        Pageable defaultPageable = PageRequest.of(0, 5, Sort.unsorted());
        when(todoService.getAll(defaultPageable)).thenReturn(getPageFromPageable(defaultPageable));

        mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content", hasSize(5))).andDo(print());
    }

    @Test
    @DisplayName("GET Request path variable = 1 trả về Todo id = 1")
    public void getOneTodoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1))).andDo(print());
    }
}
