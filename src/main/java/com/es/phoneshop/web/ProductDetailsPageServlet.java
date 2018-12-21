package com.es.phoneshop.web;

import com.es.phoneshop.dao.reviewDao.ArrayListReviewDao;
import com.es.phoneshop.dao.reviewDao.ReviewDao;
import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.service.cartService.CartService;
import com.es.phoneshop.service.cartService.CartServiceImpl;
import com.es.phoneshop.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.viewedProduct.ViewedProductList;
import com.es.phoneshop.service.reviewService.ReviewService;
import com.es.phoneshop.service.reviewService.ReviewServiceImp;
import com.es.phoneshop.service.viewedProductService.ViewedProductService;
import com.es.phoneshop.service.viewedProductService.ViewedProductServiceImpl;
import com.es.phoneshop.util.ProductLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductDetailsPageServlet extends HttpServlet {
    private final static String PRODUCT_ATTRIBUTE = "product";
    private final static String QUANTITY_ERROR_ATTRIBUTE = "quantityError";
    private final static String QUANTITY_PARAMETER = "quantity";

    private CartService cartService;
    private ProductLoader productLoader;
    private ReviewDao reviewDao;

    @Override
    public void init() throws ServletException {
        super.init();

        cartService = CartServiceImpl.getInstance();
        productLoader = ProductLoader.getInstance();
        reviewDao = ArrayListReviewDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = productLoader.loadProductFromURL(request);
            request.setAttribute(PRODUCT_ATTRIBUTE, product);
            System.out.println(reviewDao.getProductReview(product.getId()));
            request.setAttribute("rewies", reviewDao.getProductReview(product.getId()));
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            response.sendError(404);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = productLoader.loadProductFromURL(request);
        HttpSession httpSession = request.getSession();
        Cart cart = cartService.getCart(httpSession);

        request.setAttribute(PRODUCT_ATTRIBUTE, product);
        request.setAttribute("rewies", reviewDao.getProductReview(product.getId()));

        Integer quantity = null;
        boolean isErrorInStockCount = true;
        try {
            String quantityStirng = request.getParameter(QUANTITY_PARAMETER);
            quantity = Integer.valueOf(quantityStirng);
        } catch (NumberFormatException e) {
            request.setAttribute(QUANTITY_ERROR_ATTRIBUTE, "Not a number");
        }

        try {
            cartService.addToCart(cart, product, quantity);
            response.sendRedirect(request.getRequestURI() + "?message=add to cart Successfully");
            isErrorInStockCount = false;
        } catch (IllegalStockArgumentException e) {
            request.setAttribute(QUANTITY_ERROR_ATTRIBUTE, "Not enough stocks");
        }

        if (isErrorInStockCount) {
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
        }
    }
}
