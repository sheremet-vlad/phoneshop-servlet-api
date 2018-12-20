package com.es.phoneshop.web;

import com.es.phoneshop.dao.productDao.ProductDao;
import com.es.phoneshop.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.service.cartService.CartService;
import com.es.phoneshop.util.ProductLoader;
import org.junit.After;
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
public class ProductDetailsPageServletTest {
    @InjectMocks
    private static ProductDetailsPageServlet servlet;

    @Mock
    private ProductDao productDao;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private Product product;
    @Mock
    private ProductLoader productLoader;
    @Mock
    private CartService cartService;

    @Before
    public void setup() {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @After
    public void tearDown() {
        productDao.deleteAll();
    }

    @Test
    public void testDoGetValidProduct() throws ServletException, IOException {
        when(productLoader.loadProductFromURL(request)).thenReturn(product);
        servlet.doGet(request, response);

        verify(request).setAttribute("product", product);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoGetProductNotExist() throws ServletException, IOException {
        when(productLoader.loadProductFromURL(request)).thenThrow(IllegalArgumentException.class);

        servlet.doGet(request, response);

        verify(response).sendError(404);
    }

    @Test
    public void testDoGetInvalidProductId() throws ServletException, IOException {
        when(productLoader.loadProductFromURL(request)).thenThrow(NumberFormatException.class);

        servlet.doGet(request, response);

        verify(response).sendError(404);
    }

    @Test
    public void testDoPostSendRedirectWhenOK() throws ServletException, IOException, IllegalStockArgumentException {
        String quantity = "1";
        when(productLoader.loadProductFromURL(request)).thenReturn(product);
        when(request.getParameter("quantity")).thenReturn(quantity);

        servlet.doPost(request, response);

        verify(cartService).addToCart(any(), eq(product), eq(Integer.valueOf(quantity)));
        verify(response).sendRedirect(anyString());
    }

    @Test
    public void testDoPostQuantityError() throws ServletException, IOException {
        when(request.getParameter("quantity")).thenThrow(NumberFormatException.class);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("quantityError"), anyString());
        verify(response).sendRedirect(anyString());
    }

}