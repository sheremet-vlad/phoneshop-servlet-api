package com.es.phoneshop.dao.productDao;

import com.es.phoneshop.dao.DaoImpl;
import com.es.phoneshop.model.product.Product;

import java.util.List;

/**
 * This class extends {@link DaoImpl} class
 * by imposes method to work with {@link Product}
 *
 * @param <T> type of object with which work database.
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public abstract class ProductDao<T extends Product> extends DaoImpl<T> {

    /**
     * Method finds entities from the database.
     * If query not null, it finds
     * entities which descriptions contains query.
     * It also can sorts product by price or description.
     * Each parameters may be null.
     *
     * @param query is a parameter which will be search in the
     *              product description. Query may have a lot of words, then
     *              it will search at least one match of the word in the
     *              description.
     * @param order sets the sorting direction.
     * @param sort by this parameter method will be sort list
     *
     * @return {@code List} list of entities.
     */
    public abstract List<T> findEntities(String query, String order, String sort);
}
