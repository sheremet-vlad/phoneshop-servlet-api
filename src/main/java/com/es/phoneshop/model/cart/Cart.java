package com.es.phoneshop.model.cart;

import com.es.phoneshop.model.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is used to contain
 * cart items and total price
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public class Cart extends Entity {
    /** List of cart items*/
    private List<CartItem> cartItems = new ArrayList<>();
    /** Total price of all cart items*/
    private BigDecimal totalPrice = new BigDecimal(0);

    /**
     * Method returns list of cart items from cart
     *
     * @return {@code List} list of cart items
     */
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Method sets list of cart items to cart.
     *
     * @param cartItems list of cart items
     *
     */
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * Method returns total price of all cart items from cart
     *
     * @return {@code BigDecimal} total price
     *
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Method sets total price to cart
     *
     * @param totalPrice total price of all cart items
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartItems, cart.cartItems)
                && Objects.equals(totalPrice, cart.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItems, totalPrice);
    }
}
