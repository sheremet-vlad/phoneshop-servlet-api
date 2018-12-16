package com.es.phoneshop.web;

import com.es.phoneshop.dao.orderDao.ArrayListOrderDao;
import com.es.phoneshop.dao.orderDao.OrderDao;
import com.es.phoneshop.model.order.Order;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderOverviewPageServlet extends HttpServlet {
    private final static String ORDER_ATTRIBUTE = "order";

    private OrderDao<Order> orderDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        orderDao = ArrayListOrderDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = loadOrderFromSecureId(request);
        request.setAttribute(ORDER_ATTRIBUTE, order);

        request.getRequestDispatcher("/WEB-INF/pages/orderOverview.jsp").forward(request, response);
    }

    private Order loadOrderFromSecureId(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int indexOfIdInUri = request.getContextPath().length() + request.getServletPath().length() + 1;
        String secureId = uri.substring(indexOfIdInUri);
        return orderDao.getEntity(secureId);
    }
}
