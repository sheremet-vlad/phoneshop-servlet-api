package com.es.phoneshop.model.viewedProduct;

import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpSession;

public interface ViewedProductService {
    ViewedProductList getViewedProductList(HttpSession session);
    void addToViewed(Product product, ViewedProductList productList);
}
