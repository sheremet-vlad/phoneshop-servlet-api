package com.es.phoneshop.util;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.http.HttpServletRequest;

public class ProductLoader {
    private ProductDao productDao = ArrayListProductDao.getInstance();

    public Product loadProductFromURL(HttpServletRequest request) throws IllegalArgumentException{
        String uri = request.getRequestURI();
        int indexOfIdInUri = request.getContextPath().length() + request.getServletPath().length() + 1;
        String stringId = uri.substring(indexOfIdInUri);
        Long id = Long.parseLong(stringId);
        return productDao.getProduct(id);
    }
}
