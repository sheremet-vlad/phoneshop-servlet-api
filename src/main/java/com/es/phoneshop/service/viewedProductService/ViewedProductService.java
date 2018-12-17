package com.es.phoneshop.service.viewedProductService;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.viewedProduct.ViewedProductList;

import javax.servlet.http.HttpSession;

/**
 * This interface imposes methods
 * to work with ViewedProductList
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public interface ViewedProductService {
    /**
     * Method returns ViewedProductList from
     * http session
     *
     * @param session session where store ViewedProductList
     *
     * @return {@code ViewedProductList} returns ViewedProductList from
     *  http session
     */
    ViewedProductList getViewedProductList(HttpSession session);

    /**
     * Method adds product to the top of the list of viewed
     * product. If product was in the list, then he push to
     * the top. List has max size, if index of product in list
     * more than max size, than product will be remove from list
     *
     * @param product this product will be add to product list
     * @param productList list of product
     */
    void addToViewed(Product product, ViewedProductList productList);
}
