package com.es.phoneshop.dao;

import com.es.phoneshop.model.Entity;
import com.es.phoneshop.model.product.Product;

import java.util.List;

/**
 * This interface imposes methods
 * to work with database. All methods
 * can work with objects, that realizes
 * {@link Entity} interface
 *
 * @param <T> type of object. It is defines which
 *           particular dao implementation will be
 *           created.
 */
public interface Dao<T extends Entity> {

    /**
     * Method gets entity from database
     * by id
     *
     * @param id {@code Long} id by which search entity
     *
     * @return {@code T} entity from database with preset id
     *
     * @throws IllegalArgumentException if entity with
     *   preset id not found in the database
     */
    T getEntity(Long id);

    /**
     * Method saves entity in the database.
     * If entity with such id exist in the
     * database, then entity will not save.
     *
     * @param entity this entity will be save
     *               in the database
     */
    void save(T entity);

    /**
     * Method deletes entity by id
     * from database.
     *
     * @param id id by which delete entity
     */
    void delete(Long id);

    /**
     * Methods deletes all entities
     * from the database
     */
    void deleteAll();
}
