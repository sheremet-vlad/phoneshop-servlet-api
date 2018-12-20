package com.es.phoneshop.util;

import com.es.phoneshop.dao.productDao.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.dao.productDao.ProductDao;

import javax.servlet.http.HttpServletRequest;

/**
 * This class used to findd product
 * by different product attribute from
 * sting and return this product. Class
 * is a singleton.
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public class ProductLoader {
    /**  container of product list */
    private ProductDao<Product> productDao = ArrayListProductDao.getInstance();

    /** contain instance of ProductLoader*/
    private static volatile ProductLoader productLoader;

    /** used to Double-Checked Locking singleton*/
    private final static Object lock = new Object();

    /** Private method creates object*/
    private ProductLoader(){

    }

    /**
     * Method realizes singleton pattern.
     * If productLoader is null, then method creates new
     * productLoader. Returns instance of ProductLoader.
     *
     * @return {@code ProductLoader} instance of ProductLoader
     */
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

    /**
     * Method gets url from request and
     * loads product by id from url and return
     * loaded product.
     *
     * @param request http servlet request, from this parameter
     *                method gets url
     * @return {@code Product} return product loaded from dao
     *
     * @throws IllegalArgumentException if url not contains id or
     *  product with this id not exist
     */
    public Product loadProductFromURL(HttpServletRequest request) throws IllegalArgumentException{
        String uri = request.getRequestURI();
        int indexOfIdInUri = request.getContextPath().length() + request.getServletPath().length() + 1;
        String stringId = uri.substring(indexOfIdInUri);
        Long id = Long.parseLong(stringId);
        return productDao.getEntity(id);
    }
}
