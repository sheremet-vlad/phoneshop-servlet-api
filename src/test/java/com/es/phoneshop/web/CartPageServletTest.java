package com.es.phoneshop.web;

import com.es.phoneshop.dao.productDao.ProductDao;
import com.es.phoneshop.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.service.cartService.CartService;
import com.es.phoneshop.util.ProductLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.product.Product;
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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartPageServletTest {
    @InjectMocks
    private CartPageServlet servlet;

    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private CartService cartService;
    @Mock
    private Cart cart;
    @Mock
    private ProductDao<Product> productDao;
    @Mock
    private Product product;

    @Before
    public void setUp() {
        when(cartService.getCart(any())).thenReturn(cart);
        when(productDao.getEntity(anyLong())).thenReturn(product);

        String[] nums = new String[]{"1", "2", "3"};

        when(request.getParameterValues("productId")).thenReturn(nums);
        when(request.getParameterValues("quantity")).thenReturn(nums);

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        servlet.init();
        servlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPost() throws ServletException, IOException, IllegalStockArgumentException {
        servlet.doPost(request, response);
        verify(cartService, times(3)).updateCart(eq(cart), any(Product.class), anyInt());
        verify(response).sendRedirect(anyString());
        verify(request).setAttribute(eq("errors"), any());
    }

    @Test
    public void testDoPostNotEnoughStock() throws ServletException, IOException, IllegalStockArgumentException{
        doThrow(IllegalStockArgumentException.class).when(cartService).updateCart(any(), any(), anyInt());

        servlet.doPost(request, response);

        verify(requestDispatcher).forward(request, response);
    }
}
