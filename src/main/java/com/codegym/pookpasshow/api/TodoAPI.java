package com.codegym.pookpasshow.api;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.services.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Todo getOneTodo(@PathVariable("id") int id) {
        return this.todoService.getOne(id);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Todo addOneTodo(@Validated @RequestBody Todo todo, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException();
        }
        return todoService.addOne(todo);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Todo patchOneTodo(@Validated @RequestBody Todo todo, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException();
        }
        return todoService.updateOne(todo);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    String deleteOneTodo(@PathVariable("id") int id) {
        todoService.deleteOne(id);
        return "{\"status\":\"deleted\"}";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleForbiddenException() {
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleNoContentException() {
    }

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException() {
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleJsonParseErrorException() {
    }
}
