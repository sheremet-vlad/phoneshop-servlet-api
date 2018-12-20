package com.es.phoneshop.web;

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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteCartItemServletTest {
    @InjectMocks
    private DeleteCartItemServlet servlet;

    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpSession session;
    @Mock
    private CartService cartService;
    @Mock
    private Cart cart;
    @Mock
    private ProductLoader productLoader;
    @Mock
    private Product product;

    @Before
    public void setUp() {
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        when(cartService.getCart(any())).thenReturn(cart);
        when(productLoader.loadProductFromURL(request)).thenReturn(product);

        servlet.doPost(request, response);

        verify(response).sendRedirect(anyString());
        verify(request).setAttribute(eq("cart"), any());
    }
}
