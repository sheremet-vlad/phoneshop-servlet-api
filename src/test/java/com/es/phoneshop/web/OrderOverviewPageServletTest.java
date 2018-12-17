package com.es.phoneshop.web;

import com.es.phoneshop.dao.orderDao.OrderDao;
import com.es.phoneshop.model.order.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderOverviewPageServletTest {
    @InjectMocks
    private OrderOverviewPageServlet servlet;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private OrderDao<Order> orderDao;
    @Mock
    private Order order;

    @Before
    public void setup(){
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        servlet.init();

        when(request.getRequestURI()).thenReturn("wsv");
        when(request.getContextPath()).thenReturn("w");
        when(request.getServletPath()).thenReturn("s");
        when(orderDao.getEntity(anyString())).thenReturn(order);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("order"), any());
        verify(requestDispatcher).forward(request, response);
    }
}
