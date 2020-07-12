package com.codegym.pookpasshow.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GenericService<T> {
    List<T> getAll();

    List<T> getAll(Sort sort);

    Page<T> getAll(Pageable pageable);

    T getOne(int id);

    T addOne(T model);

    T updateOne(T model);

    T updateOne(int id);

    void deleteOne(T model);

    void deleteOne(int id);
}
