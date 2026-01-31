package com.cinema.dao;

import java.util.List;

/**
 *
 * @author meriem
 */
public interface IDao<T> {

    boolean create(T o);

    boolean update(T o);

    boolean delete(T o);

    T findById(int id);

    List<T> findAll();
}
