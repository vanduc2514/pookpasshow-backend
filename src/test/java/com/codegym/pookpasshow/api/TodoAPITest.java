package com.codegym.pookpasshow.api;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.services.todo.TodoService;
import org.junit.jupiter.api.BeforeAll;
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

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestPropertySource("classpath:application.properties")
public class TodoAPITest {
    public static final int SAMPLE_TODO_LIST_SIZE = 10;
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    private static List<Todo> todoList;

    private final int defaultPageSize = 5;

    private int pageRequestNumber;

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

    private Page<Todo> getPageFromPageRequest(Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), todoList.size());
        return new PageImpl<>(todoList.subList(start, end), pageable, todoList.size());
    }

    @Test
    @DisplayName("/todos trả về page mặc định thứ 1")
    public void getTodoPage() throws Exception {
        pageRequestNumber = 0;
        Pageable defaultPageable = PageRequest.of(pageRequestNumber, defaultPageSize, Sort.unsorted());
        when(todoService.getAll(defaultPageable)).thenReturn(getPageFromPageRequest(defaultPageable));

        mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageable.paged", is(true)))
                .andExpect(jsonPath("$.pageable.pageSize", is(defaultPageSize)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(pageRequestNumber)))
                .andExpect(jsonPath("$.content", hasSize(defaultPageSize)))
                .andExpect(jsonPath("$.totalElements", is(SAMPLE_TODO_LIST_SIZE)))
                .andDo(print());
    }

    @Test
    @DisplayName("/todos?page=0 trả về page thứ nhất")
    public void getTodoPageOne() throws Exception {
        pageRequestNumber = 0;
        Pageable pageRequest = PageRequest.of(pageRequestNumber, defaultPageSize);
        when(todoService.getAll(pageRequest)).thenReturn(getPageFromPageRequest(pageRequest));

        mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                .param("page", String.valueOf(pageRequestNumber))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageable.paged", is(true)))
                .andExpect(jsonPath("$.pageable.pageSize", is(defaultPageSize)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(pageRequestNumber)))
                .andExpect(jsonPath("$.content", hasSize(defaultPageSize)))
                .andExpect(jsonPath("$.totalElements", is(SAMPLE_TODO_LIST_SIZE)))
                .andDo(print());
    }

    @Test
    @DisplayName("/todos?page=1 trả về page thứ hai")
    public void getTodoPageTwo() throws Exception {
        pageRequestNumber = 1;
        Pageable pageRequest = PageRequest.of(pageRequestNumber, defaultPageSize);
        when(todoService.getAll(pageRequest)).thenReturn(getPageFromPageRequest(pageRequest));

        mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                .param("page", String.valueOf(pageRequestNumber))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageable.paged", is(true)))
                .andExpect(jsonPath("$.pageable.pageSize", is(defaultPageSize)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(pageRequestNumber)))
                .andExpect(jsonPath("$.content", hasSize(defaultPageSize)))
                .andExpect(jsonPath("$.totalElements", is(SAMPLE_TODO_LIST_SIZE)))
                .andDo(print());
    }

    @Test
    @DisplayName("/todos?page=abc trả về Bad Request")
    public void getBadRequestWithInvalidParamTypeTest() throws Exception {
        String valueToFail = "abc";
        mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                .param("page", valueToFail)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    @DisplayName("/todos/1 trả về Todo có id = 1")
    public void getOneTodoTest() throws Exception {
        int todoId = 1;
        when(todoService.getOne(todoId)).thenReturn(todoList.get(todoId));
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/{id}", todoId)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(todoId)))
                .andExpect(jsonPath("$.title", notNullValue()))
                .andExpect(jsonPath("$.content", notNullValue()))
                .andExpect(jsonPath("$.completed", notNullValue()))
                .andDo(print());
    }

    @Test
    @DisplayName("/todos/99 trả về status not found")
    public void getNotFoundTest() throws Exception {
        int failedID = 99;
        when(todoService.getOne(99)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/todos/{id}", failedID)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}
