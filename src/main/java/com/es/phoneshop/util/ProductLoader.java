package com.es.phoneshop.util;

import com.es.phoneshop.dao.productDao.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.dao.productDao.ProductDao;

import javax.servlet.http.HttpServletRequest;

public class ProductLoader {
    private ProductDao productDao = ArrayListProductDao.getInstance();

    private static volatile ProductLoader productLoader;
    private final static Object lock = new Object();

    private ProductLoader(){

    }

    public static ProductLoader getInstance() {
        if (productLoader == null) {
            synchronized (lock) {
                if (productLoader == null) {
                    productLoader = new ProductLoader();
                }
            }
        }

        return productLoader;
    }

    public Product loadProductFromURL(HttpServletRequest request) throws IllegalArgumentException{
        String uri = request.getRequestURI();
        int indexOfIdInUri = request.getContextPath().length() + request.getServletPath().length() + 1;
        String stringId = uri.substring(indexOfIdInUri);
        Long id = Long.parseLong(stringId);
        return productDao.getProduct(id);
    }
}
