package com.es.phoneshop.dao.orderDao;

import com.es.phoneshop.dao.DaoImpl;
import com.es.phoneshop.model.order.Order;

/**
 * This class extends {@link DaoImpl} class
 * by imposes method to work with {@link Order}
 *
 * @param <T> type of object with which work database.
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public abstract class OrderDao<T extends Order> extends DaoImpl<T> {

    /**
     * Method gets entity from database
     * by secure id
     *
     * @param id {code String} secure id by which search entity
     *
     * @return {@code T} entity from database with preset secure id
     *
     * @throws IllegalArgumentException if entity with
     *   preset id not found in the database
     */
    public abstract T getEntity(String id);
}
