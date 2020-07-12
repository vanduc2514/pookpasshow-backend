package com.codegym.pookpasshow.services.todo;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.repositories.TodoRepository;
import com.codegym.pookpasshow.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TodoServiceImp implements TodoService {
    private TodoRepository todoRepository;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> todoList = new LinkedList<>();
        Iterable<Todo> iterable = todoRepository.findAll();
        for (Todo todo : iterable) {
            todoList.add(todo);
        }
        return todoList;
    }

    @Override
    public List<Todo> getAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Todo> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Todo getOne(int id) {
        return null;
    }

    @Override
    public Todo addOne(Todo model) {
        return null;
    }

    @Override
    public Todo updateOne(Todo model) {
        return null;
    }

    @Override
    public Todo updateOne(int id) {
        return null;
    }

    @Override
    public void deleteOne(Todo model) {

    }

    @Override
    public void deleteOne(int id) {

    }
}