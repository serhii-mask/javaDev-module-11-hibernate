package org.example.repository;


import java.util.List;

public abstract interface GenericDao<T> {
    boolean create(T object);

    boolean update(T object);

    T getById(Object id);

    List<T> getAll();

    void deleteById(Object id);

    void delete(T object);
}