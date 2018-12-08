package com.es.phoneshop.web;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartService;
import com.es.phoneshop.model.cart.CartServiceImpl;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.util.ProductLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCartItemServlet extends HttpServlet {
    private final static String MESSAGE_DELETE_SUCCESSFULLY="/cart?message=product delete successfully";
    private final static String CART_ATTRIBUTE = "cart";

    private CartService cartService;
    private ProductLoader productLoader;
    @Override
    public void init() throws ServletException {
        super.init();

        cartService = CartServiceImpl.getInstance();
        productLoader = new ProductLoader();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           Product product = productLoader.loadProductFromURL(request);
           Cart cart = cartService.getCart(request.getSession());
           cartService.delete(cart, product);
           request.setAttribute(CART_ATTRIBUTE, cart);
           response.sendRedirect(request.getContextPath() + MESSAGE_DELETE_SUCCESSFULLY);
       } catch (IllegalArgumentException e) {
           response.sendError(404);
       }
    }
}
