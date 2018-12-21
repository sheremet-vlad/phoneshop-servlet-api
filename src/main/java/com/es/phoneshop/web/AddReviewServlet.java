package com.es.phoneshop.web;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.service.cartService.CartService;
import com.es.phoneshop.service.cartService.CartServiceImpl;
import com.es.phoneshop.service.reviewService.ReviewService;
import com.es.phoneshop.service.reviewService.ReviewServiceImp;
import com.es.phoneshop.util.ProductLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddReviewServlet extends HttpServlet {

    private ReviewService reviewService;
    private ProductLoader productLoader;
    @Override
    public void init() throws ServletException {
        super.init();

        reviewService = ReviewServiceImp.getInstance();
        productLoader = ProductLoader.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = productLoader.loadProductFromURL(request);

        String name = request.getParameter("reviewName");
        String comments = request.getParameter("comments");
        int mark = Integer.parseInt(request.getParameter("reviewMark"));
        Long productId = product.getId();

        reviewService.placeReview(productId, name, comments, mark);

        response.sendRedirect(request.getContextPath() + "/products/" + product.getId());
    }
}
