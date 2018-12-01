package com.es.phoneshop.model.cart;

import com.es.phoneshop.model.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CartServiceImpl implements CartService {

    private final static String CART_ATTRIBUTE = "cart";

    private static volatile CartService cartService = null;
    private final static Object lock = new Object();

    private CartServiceImpl(){
    }

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
        if(currentStock < quantity) throw new IllegalStockArgumentException();

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

    }
}
