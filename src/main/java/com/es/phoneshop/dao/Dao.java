package com.es.phoneshop.dao;

import com.es.phoneshop.model.product.Product;

import java.util.List;

public interface Dao {
    Product getProduct(Long id);
    List<Product> findProducts(String query, String order, String sort) ;
    void save(Product product);
    void delete(Long id);
    void deleteAll();
}
