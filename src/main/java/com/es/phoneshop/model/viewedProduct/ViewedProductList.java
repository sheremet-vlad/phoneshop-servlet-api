package com.es.phoneshop.model.viewedProduct;

import com.es.phoneshop.model.product.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class used to store
 * list of recently viewed product
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public class ViewedProductList implements Serializable {
    /**list of recently viewed product*/
    private List<Product> viewedProduct = new ArrayList<>();

    /**
     * Method returns list of recently viewed
     * product
     *
     * @return {@code List} list of recently viewed product
     */
    public List<Product> getViewedProduct() {
        return viewedProduct;
    }

    /**
     * Method sets passed parameter to
     * list of recently viewed product
     *
     * @param viewedProduct this parameter will set to
     *                      list of recently viewed product
     */
    public void setViewedProduct(List<Product> viewedProduct) {
        this.viewedProduct = viewedProduct;
    }
}
