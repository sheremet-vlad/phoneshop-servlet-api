package com.es.phoneshop.dao;

import com.es.phoneshop.model.Entity;
import com.es.phoneshop.model.product.Product;

import java.util.List;

public interface Dao<T extends Entity> {
    T getEntity(Long id);
    void save(T entity);
    void delete(Long id);
    void deleteAll();
}
