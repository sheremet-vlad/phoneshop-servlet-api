package com.es.phoneshop.service.cartService;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

/**
 * This interface imposes methods
 * to work with cart
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public interface CartService {
    /**
     * Method returns cart from http session
     *
     * @param httpSession session where store cart
     *
     * @return {@code Cart} cart from http session
     */

    Cart getCart(HttpSession httpSession);

    /**
     * Method adds product and product Quantity  to cart.
     *
     * @param cart cart where will be add product
     * @param product product will be add to cart
     * @param quantity product quantity, should be not null,
     *                 more then zero and less then product stock
     *
     * @throws IllegalStockArgumentException if quantity more than
     *  product stock or quantity is null or quantity less then zero
     *
     */
    void addToCart(Cart cart, Product product, Integer quantity) throws IllegalStockArgumentException;

    /**
     * Method updates product quantity in the cart
     *
     * @param cart cart where will be update product quantity
     * @param product product which quantity will be update
     * @param quantity new product quantity, should
     *                 be not null, more then zero
     *                 and less then product stock
     *
     * @throws IllegalStockArgumentException if quantity more than
     *  product stock or quantity is null or quantity less then zero
     * @throws NoSuchElementException if product not found in the cart
     */
    void updateCart(Cart cart, Product product, Integer quantity) throws IllegalStockArgumentException, NoSuchElementException;

    /**
     * Method removes product from cart
     *
     * @param cart cart where will be delete product
     * @param product product which will be delete
     */
    void delete(Cart cart, Product product);

    /**
     * Method calculates total price
     * of all cart items in the cart and
     * set this value to the cart
     *
     * @param cart cart where will be recalculate
     *             total price
     */
    void recalculateCart(Cart cart);

    /**
     * Method clears list of cart items
     * and total price in cart
     *
     * @param cart cart which will be emptied
     */
    void clearCart(Cart cart);
}
