package com.es.phoneshop.web;

import com.es.phoneshop.dao.productDao.ArrayListProductDao;
import com.es.phoneshop.dao.productDao.ProductDao;
import com.es.phoneshop.model.product.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListPageServlet extends HttpServlet {
    private final static String SEARCH_QUERY = "query";
    private final static String SORT_PARAMETER = "sort";
    private final static String ORDER_PARAMETER = "order";

    private ProductDao<Product> productDao;

    @Override
    public void init() throws ServletException {
        super.init();

        productDao = ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter(SEARCH_QUERY);
        String sort = request.getParameter(SORT_PARAMETER);
        String order = request.getParameter(ORDER_PARAMETER);

        request.setAttribute("products", productDao.findEntities(query, order, sort));
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}