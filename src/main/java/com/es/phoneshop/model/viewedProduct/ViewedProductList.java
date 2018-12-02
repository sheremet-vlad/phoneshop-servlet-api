package com.es.phoneshop.model.viewedProduct;

import com.es.phoneshop.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ViewedProductList {
    private List<Product> viewedProduct = new ArrayList<>();

    public List<Product> getViewedProduct() {
        return viewedProduct;
    }

    public void setViewedProduct(List<Product> viewedProduct) {
        this.viewedProduct = viewedProduct;
    }
}
