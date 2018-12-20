package com.es.phoneshop.web;

import com.es.phoneshop.exception.IllegalStockArgumentException;
import com.es.phoneshop.service.cartService.CartService;
import com.es.phoneshop.service.orderService.OrderService;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutPageServletTest {
    private final static String NAME_ATTRIBUTE = "name";
    private final static String DELIVERY_ADDRESS_ATTRIBUTE = "deliveryAddress";

    @InjectMocks
    private static CheckoutPageServlet servlet;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private CartService cartService;
    @Mock
    private OrderService orderService;

    @Before
    public void setup() {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGetValidProduct() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPosWhenOneOfFieldsIsEmpty() throws ServletException, IOException, IllegalStockArgumentException {
        String name = "a";
        String deliveryAddress = "b";
        when(request.getParameter(NAME_ATTRIBUTE)).thenReturn(name);
        when(request.getParameter(DELIVERY_ADDRESS_ATTRIBUTE)).thenReturn(deliveryAddress);
        when(orderService.checkParameters(name, null, deliveryAddress, null)).thenReturn(false);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("errorMessage"), anyString());
        verify(requestDispatcher).forward(request, response);
    }
}
