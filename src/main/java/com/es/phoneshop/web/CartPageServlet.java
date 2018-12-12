package com.es.phoneshop.web;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartService;
import com.es.phoneshop.model.cart.CartServiceImpl;
import com.es.phoneshop.model.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CartPageServlet extends HttpServlet {
    private final static String PRODUCTS_ID_PARAMETER = "productId";
    private final static String QUANTITY_PARAMETER = "quantity";
    private final static String MESSAGE_QUANTITY_NOT_NUMBER = "not a number";
    private final static String MESSAGE_NOT_ENOUGH_STOCK = "not enough stock";
    private final static String MESSAGE_PRODUCT_NOT_FOUND = "product no found, please reload page";
    private final static String ERRORS_ATTRIBUTE = "errors";
    private final static String CART_ATTRIBUTE = "cart";
    private final static String MESSAGE_CART_UPGRADE_SUCCESSFULLY = "?message=cart upgrade successfully";

    private CartService cartService;
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        super.init();

        cartService = CartServiceImpl.getInstance();
        productDao = ArrayListProductDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = cartService.getCart(request.getSession());
        showPage(request, response, cart);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] productsId = request.getParameterValues(PRODUCTS_ID_PARAMETER);
        String[] quantities = request.getParameterValues(QUANTITY_PARAMETER);
        Cart cart = cartService.getCart(request.getSession());
        Map<Long, String> errors = new HashMap<>();

        for (int i = 0; i < productsId.length; i++) {
            Long productId = Long.valueOf(productsId[i]);
            Product product = productDao.getProduct(productId);
            Integer quantity = null;
            try {
                quantity = Integer.valueOf(quantities[i]);
            } catch (NumberFormatException e) {
                errors.put(productId, MESSAGE_QUANTITY_NOT_NUMBER);
            }

            if (quantity != null) {
                try {
                    cartService.updateCart(cart, product, quantity);
                } catch (NoSuchElementException e) {
                    errors.put(productId, MESSAGE_PRODUCT_NOT_FOUND);
                } catch (IllegalStockArgumentException e) {
                    errors.put(productId, MESSAGE_NOT_ENOUGH_STOCK);
                }
            }
        }

        request.setAttribute(ERRORS_ATTRIBUTE, errors);

        if (errors.isEmpty()) {
            response.sendRedirect(request.getRequestURI() + MESSAGE_CART_UPGRADE_SUCCESSFULLY);
        } else {
            showPage(request, response, cart);
        }

    }


    private void showPage(HttpServletRequest request, HttpServletResponse response, Cart cart) throws ServletException, IOException {
        request.setAttribute(CART_ATTRIBUTE, cart);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }
}
