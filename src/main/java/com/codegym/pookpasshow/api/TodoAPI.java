package com.codegym.pookpasshow.api;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.services.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoAPI {
    private TodoService todoService;

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(params = "page", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Todo> getTodoPage(Pageable pageable) {
        return todoService.getAll(pageable);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Todo> getDefaultTodoPage(Pageable pageable) {
        return todoService.getAll(pageable);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Todo getOneTodo(@PathVariable("id") int id) {
        return todoService.getOne(id);
    }
}
