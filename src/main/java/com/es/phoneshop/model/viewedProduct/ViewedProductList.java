package com.es.phoneshop.model.viewedProduct;

import com.es.phoneshop.model.product.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ViewedProductList implements Serializable {
    private List<Product> viewedProduct = new ArrayList<>();

    public List<Product> getViewedProduct() {
        return viewedProduct;
    }

    public void setViewedProduct(List<Product> viewedProduct) {
        this.viewedProduct = viewedProduct;
    }
}
