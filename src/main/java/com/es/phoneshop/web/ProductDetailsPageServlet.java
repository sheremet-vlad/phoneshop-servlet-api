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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProductDetailsPageServlet extends HttpServlet {

    private ProductDao productDao;
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        super.init();

        productDao = ArrayListProductDao.getInstance();
        cartService = CartServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = loadProduct(request);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            response.sendError(404, e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = loadProduct(request);
        HttpSession httpSession = request.getSession();
        Cart cart = cartService.getCart(httpSession);

        request.setAttribute("product", product);
        request.setAttribute("cart", cart.getCartItems());

        Integer quantity = null;
        boolean isErrorInStockCount = true;
        try {
            String quantityStirng = request.getParameter("quantity");
            quantity = Integer.valueOf(quantityStirng);
        } catch (NumberFormatException e) {
            request.setAttribute("quantityError", "Not a number");
        }

        if (quantity != null) {
            try {
                cartService.addToCart(cart, product, quantity);
                response.sendRedirect(request.getRequestURI() + "?message=add to cart Successfully");
                isErrorInStockCount = false;
            } catch (IllegalStockArgumentException e) {
                request.setAttribute("quantityError", "Not enough stocks");
            }
        }

        if (isErrorInStockCount) {
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
        }
    }

    private Product loadProduct(HttpServletRequest request) throws IllegalArgumentException {
        String uri = request.getRequestURI();
        int indexOfIdInUri = request.getContextPath().length() + request.getServletPath().length() + 1;
        String stringId = uri.substring(indexOfIdInUri);
        Long id = Long.parseLong(stringId);
        return productDao.getProduct(id);
    }
}
