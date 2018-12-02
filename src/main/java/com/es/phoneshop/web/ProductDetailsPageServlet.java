package com.es.phoneshop.web;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartService;
import com.es.phoneshop.model.cart.CartServiceImpl;
import com.es.phoneshop.model.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;
import com.es.phoneshop.model.viewedProduct.ViewedProductList;
import com.es.phoneshop.model.viewedProduct.ViewedProductService;
import com.es.phoneshop.model.viewedProduct.ViewedProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductDetailsPageServlet extends HttpServlet {
    private final static String VIEWED_PRODUCT_ATTRIBUTE = "viewedProducts";
    private final static String PRODUCT_ATTRIBUTE = "product";
    private final static String CART_ATTRIBUTE = "cart";
    private final static String QUANTITY_ERROR_ATTRIBUTE = "quantityError";
    private final static String QUANTITY_PARAMETER = "quantity";

    private ProductDao productDao;
    private CartService cartService;
    private ViewedProductService viewedProductService;

    @Override
    public void init() throws ServletException {
        super.init();

        productDao = ArrayListProductDao.getInstance();
        cartService = CartServiceImpl.getInstance();
        viewedProductService = ViewedProductServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = loadProduct(request);
            List<Product> viewedList = loadViewedList(request);
            request.setAttribute(PRODUCT_ATTRIBUTE, product);
            request.setAttribute(VIEWED_PRODUCT_ATTRIBUTE, viewedList);
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            response.sendError(404);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = loadProduct(request);
        HttpSession httpSession = request.getSession();
        Cart cart = cartService.getCart(httpSession);
        List<Product> viewedList = loadViewedList(request);

        request.setAttribute(PRODUCT_ATTRIBUTE, product);
        request.setAttribute(CART_ATTRIBUTE, cart.getCartItems());
        request.setAttribute(VIEWED_PRODUCT_ATTRIBUTE, viewedList);

        Integer quantity = null;
        boolean isErrorInStockCount = true;
        try {
            String quantityStirng = request.getParameter(QUANTITY_PARAMETER);
            quantity = Integer.valueOf(quantityStirng);
        } catch (NumberFormatException e) {
            request.setAttribute(QUANTITY_ERROR_ATTRIBUTE, "Not a number");
        }

        if (quantity != null) {
            try {
                cartService.addToCart(cart, product, quantity);
                response.sendRedirect(request.getRequestURI() + "?message=add to cart Successfully");
                isErrorInStockCount = false;
            } catch (IllegalStockArgumentException e) {
                request.setAttribute(QUANTITY_ERROR_ATTRIBUTE, "Not enough stocks");
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

    private List<Product> loadViewedList(HttpServletRequest request) {
        ViewedProductList viewedProductList = viewedProductService.getViewedProductList(request.getSession());
        return viewedProductList.getViewedProduct();
    }
}
