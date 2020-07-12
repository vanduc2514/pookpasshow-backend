package com.codegym.pookpasshow.repositories;

import com.codegym.pookpasshow.model.Todo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Integer> {

}
