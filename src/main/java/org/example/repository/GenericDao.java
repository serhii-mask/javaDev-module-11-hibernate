package org.example.repository;

import java.util.List;

public interface GenericDao<T, ID> {
    boolean create(T object);

    boolean update(T object);

    T getById(ID id);

    List<T> getAll();

    void deleteById(ID id);

    void delete(T object);
}