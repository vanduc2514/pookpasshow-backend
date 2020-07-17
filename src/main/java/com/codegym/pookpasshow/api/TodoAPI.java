package com.codegym.pookpasshow.api;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.services.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

@RestController
@RequestMapping(
        value = "/todos",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE}
)
public class TodoAPI {
    private TodoService todoService;

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    @ResponseBody
    public Page<Todo> getDefaultTodoPage(Pageable pageable) {
        return todoService.getAll(pageable);
    }

    @GetMapping(params = "page")
    @ResponseBody
    public Page<Todo> getTodoPage(@RequestParam("page") String page, Pageable pageable) {
        if (!page.matches("\\d+")) {
            throw new InvalidParameterException();
        }
        return todoService.getAll(pageable);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Todo getOneTodo(@PathVariable("id") int id) {
        return todoService.getOne(id);
    }

    @ExceptionHandler({InvalidParameterException.class, UnsatisfiedServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidParameterException() {
        return "{\"error\":\"invalid parameter\"}";
    }
}
