package com.es.phoneshop.service.cartService;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartItem;
import com.es.phoneshop.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class realize interface
 * to work with cart. It is a singleton.
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public class CartServiceImpl implements CartService {
    /** attribute to gt cart from session*/
    private final static String CART_ATTRIBUTE = "cart";

    /** contain instance of CartService*/
    private static volatile CartService cartService = null;

    /** used to Double-Checked Locking singleton*/
    private final static Object lock = new Object();

    /** Private method creates object*/
    private CartServiceImpl(){
    }

    /**
     * Method realizes singleton pattern.
     * If cartService is null, then method creates new
     * cartService. Returns instance of CartService.
     *
     * @return {@code CartService} instance of CartService
     */
    public static CartService getInstance() {
        if (cartService == null) {
            synchronized (lock) {
                if (cartService == null) {
                    cartService = new CartServiceImpl();
                }
            }
        }
        return cartService;
    }

    @Override
    public Cart getCart(HttpSession httpSession) {
        Cart cart = (Cart) httpSession.getAttribute(CART_ATTRIBUTE);

        if (cart == null) {
            cart = new Cart();
            httpSession.setAttribute(CART_ATTRIBUTE, cart);
        }

        return cart;
    }

    @Override
    public void addToCart(Cart cart, Product product, Integer quantity) throws IllegalStockArgumentException {
        int currentStock = product.getStock();

        if (isQuantityNotValid(currentStock, quantity)) {
            throw new IllegalStockArgumentException();
        }

        Optional<CartItem> optCartItem = cart.getCartItems().stream().
                filter(cartItem -> product.getId().equals(cartItem.getProduct().getId()))
                .findAny();

        if(optCartItem.isPresent()){
            CartItem cartItem = optCartItem.get();

            if(currentStock < cartItem.getQuantity() + quantity){
                throw new IllegalStockArgumentException();
            }

            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cart.getCartItems().add(new CartItem(product, quantity));
        }

        recalculateCart(cart);
    }

    /**
     * Method checks product quantity.
     *
     * @param currentStock product stock
     * @param quantity product quantity
     *
     * @return {@code boolean} If quantity less
     *   than zero or more than currentStock, or quantity
     *   is null, then it will be return false, else true.
     */
    private boolean isQuantityNotValid(Integer currentStock, Integer quantity) {
        return quantity == null
                || currentStock < quantity
                || quantity <= 0;
    }

    @Override
    public void updateCart(Cart cart, Product product, Integer quantity) throws IllegalStockArgumentException, NoSuchElementException{
        int currentStock = product.getStock();

        if (isQuantityNotValid(currentStock, quantity)) {
            throw new IllegalStockArgumentException();
        }

        Optional<CartItem> optCartItem = cart.getCartItems().stream().
                filter(cartItem -> product.getId().equals(cartItem.getProduct().getId()))
                .findAny();

        if(optCartItem.isPresent()){
            CartItem cartItem = optCartItem.get();
            cartItem.setQuantity(quantity);
        } else {
            throw new NoSuchElementException();
        }

        recalculateCart(cart);
    }

    @Override
    public void delete(Cart cart, Product product) {
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.removeIf(cartItem -> cartItem.getProduct().equals(product));
        cart.setCartItems(cartItems);
        recalculateCart(cart);
    }

    @Override
    public void recalculateCart(Cart cart) {
        BigDecimal totalPrice = cart.getCartItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalPrice(totalPrice);
    }

    @Override
    public void clearCart(Cart cart) {
        cart.getCartItems().clear();
        recalculateCart(cart);
    }
}
