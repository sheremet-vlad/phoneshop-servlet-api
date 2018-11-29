package com.es.phoneshop.web;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetailsPageServlet extends HttpServlet {

    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        super.init();

        productDao = ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        int indexOfIdInUri = request.getContextPath().length() + request.getServletPath().length() + 1;
        String stringId = uri.substring(indexOfIdInUri);
        Long id;

        try {
            id = Long.parseLong(stringId);
            request.setAttribute("product", productDao.getProduct(id));
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            response.sendError(404, e.getMessage());
        }

    }
}
