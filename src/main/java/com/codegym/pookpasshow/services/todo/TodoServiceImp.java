package com.codegym.pookpasshow.services.todo;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
    public Todo getOne(int id) throws EntityNotFoundException {
        Optional<Todo> todoFound = todoRepository.findById(id);
        if (!todoFound.isPresent()) {
            throw new EntityNotFoundException();
        }
        return todoFound.get();
    }

    @Override
    public Todo addOne(Todo todo) throws IllegalArgumentException, DataIntegrityViolationException {
        if (todo.getId() != 0) {
            throw new IllegalArgumentException();
        }
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateOne(Todo todo) throws EntityNotFoundException {
        int id = todo.getId();
        Todo todoFound = getOne(id);
        todoFound.setTitle(todo.getTitle());
        todoFound.setContent(todo.getContent());
        todoFound.setCompleted(todo.isCompleted());
        return todoRepository.save(todoFound);
    }

    @Override
    public Todo updateOne(int id, Todo todo) {
        return null;
    }

    @Override
    public void deleteOne(Todo model) {

    }

    @Override
    public void deleteOne(int id) {
        this.todoRepository.deleteById(id);
    }
}
