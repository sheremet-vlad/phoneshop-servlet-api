package com.es.phoneshop.model.product;

import com.es.phoneshop.model.exception.PhoneshopAppException;

import java.util.List;

public interface ProductDao {
    Product getProduct(Long id);
    List<Product> findProducts(String query, String order, String sort) throws PhoneshopAppException;
    void save(Product product);
    void delete(Long id);
    //http://localhost:8080/phoneshop-servlet-api/products?sort=description&order=asc&query=
    //http://localhost:8080/phoneshop-servlet-api/phoneshop-servlet-api/products?sort=description&order=asc&query=
}
