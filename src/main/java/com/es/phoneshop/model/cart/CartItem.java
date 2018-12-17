package com.es.phoneshop.model.cart;

import com.es.phoneshop.model.product.Product;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is item of cart. Cart contain
 * product and quantity if product.
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public class CartItem implements Serializable {
    /**Product of item*/
    private Product product;

    /**Quantity of product*/
    private Integer quantity;

    /**
     * Method create object with passed parameters
     *
     * @param product product of item
     * @param quantity quantity of product in item
     */
    public CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Method create object with copy values
     * from passed parameter
     *
     * @param cartItem used as copy object
     */
    public CartItem(CartItem cartItem) {
        this.product = cartItem.getProduct();
        this.quantity = cartItem.getQuantity();
    }

    /**
     * Method get product from item
     *
     * @return {@code Product} product from item
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Method set product in item
     *
     * @param product product that will be set in item
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Method get quantity of product from item
     *
     * @return {@code Integer} quantity of product in item
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Method set quantity of product in item
     *
     * @param quantity quantity that will be set in item
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return '(' +
                "product=" + product.getCode() +
                ", quantity=" + quantity +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product) &&
                Objects.equals(quantity, cartItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }
}
