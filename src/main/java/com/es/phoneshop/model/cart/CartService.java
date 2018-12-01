package com.es.phoneshop.model.cart;

import com.es.phoneshop.model.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpSession;

public interface CartService {
    Cart getCart(HttpSession httpSession);
    void addToCart(Cart cart, Product product, Integer quantity) throws IllegalStockArgumentException;
}
