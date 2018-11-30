package com.es.phoneshop.model.cart;

import com.es.phoneshop.model.product.Product;

public class CartServiceImpl implements CartService {

    private static volatile CartServiceImpl cartServiceImpl = null;

    private final static Object lock = new Object();

    private CartServiceImpl() {
        if (cartServiceImpl == null) {
            synchronized (lock) {
                if (cartServiceImpl == null) {
                    cartServiceImpl = new CartServiceImpl();
                }
            }
        }
    }
    @Override
    public Cart getCart() {
        return null;
    }

    @Override
    public void addToCart(Product product, Integer quantity) {

    }
}
