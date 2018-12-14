package com.es.phoneshop.model.cart;

import com.es.phoneshop.model.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

public interface CartService {
    Cart getCart(HttpSession httpSession);
    void addToCart(Cart cart, Product product, Integer quantity) throws IllegalStockArgumentException;
    void updateCart(Cart cart, Product product, Integer quantity) throws IllegalStockArgumentException, NoSuchElementException;
    void delete(Cart cart, Product product);
    void recalculateCart(Cart cart);
}
