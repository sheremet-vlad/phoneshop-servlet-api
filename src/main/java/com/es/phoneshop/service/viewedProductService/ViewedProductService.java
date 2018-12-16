package com.es.phoneshop.service.viewedProductService;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.viewedProduct.ViewedProductList;

import javax.servlet.http.HttpSession;

public interface ViewedProductService {
    ViewedProductList getViewedProductList(HttpSession session);
    void addToViewed(Product product, ViewedProductList productList);
}
