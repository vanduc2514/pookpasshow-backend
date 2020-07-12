package com.codegym.pookpasshow.api;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.services.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("todos")
@CrossOrigin("*")
public class TodoAPI {
    private TodoService todoService;

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<Todo> getAllTodo() {
        return todoService.getAll();
    }
}
