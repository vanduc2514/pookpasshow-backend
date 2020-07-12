package com.codegym.pookpasshow.services.todo;

import com.codegym.pookpasshow.model.Todo;
import com.codegym.pookpasshow.services.GenericService;

public interface TodoService extends GenericService<Todo> {
    Todo updateOne(int id, Todo model);
}
