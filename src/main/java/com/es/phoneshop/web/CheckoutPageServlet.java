package com.es.phoneshop.web;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.order.Order;
import com.es.phoneshop.service.cartService.CartService;
import com.es.phoneshop.service.cartService.CartServiceImpl;
import com.es.phoneshop.service.orderService.OrderService;
import com.es.phoneshop.service.orderService.OrderServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckoutPageServlet extends HttpServlet {
    private final static String NAME_ATTRIBUTE = "name";
    private final static String DELIVERY_ADDRESS_ATTRIBUTE = "deliveryAddress";
    private final static String PHONE_ATTRIBUTE = "phone";
    private final static String ERROR_MASSAGE_ATTRIBUTE = "errorMessage";
    private final static String ERROR_MESSAGE = "error, all fields should be write in";

    private CartService cartService;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        cartService = CartServiceImpl.getInstance();
        orderService = OrderServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = cartService.getCart(request.getSession());
        String name = request.getParameter(NAME_ATTRIBUTE);
        String deliveryAddress = request.getParameter(DELIVERY_ADDRESS_ATTRIBUTE);
        String phone = request.getParameter(PHONE_ATTRIBUTE);

        if (orderService.checkParameters(name, deliveryAddress, phone)) {
            Order order = orderService.placeOrder(cart, name, deliveryAddress, phone);
            cartService.clearCart(cart);
            response.sendRedirect(request.getContextPath() + "/order-overview/" + order.getSecureId());
        } else {
            request.setAttribute(ERROR_MASSAGE_ATTRIBUTE, ERROR_MESSAGE);
            request.getRequestDispatcher("/WEB-INF/pages/checkout.jsp").forward(request, response);
        }

    }


}
