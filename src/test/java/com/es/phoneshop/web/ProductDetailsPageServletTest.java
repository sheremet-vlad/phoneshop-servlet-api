package com.es.phoneshop.web;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.dao.productDao.ProductDao;
import com.es.phoneshop.model.viewedProduct.ViewedProductList;
import com.es.phoneshop.service.viewedProductService.ViewedProductService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductDetailsPageServletTest {
    @InjectMocks
    private static ProductDetailsPageServlet servlet;

    @Mock
    private ProductDao productDao;
    @Mock
    private ViewedProductService viewedProductService;
    @Mock
    private ViewedProductList viewedProductList;

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

    @Before
    public void setup() {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(viewedProductService.getViewedProductList(any())).thenReturn(viewedProductList);
    }

    @After
    public void tearDown(){
        productDao.deleteAll();
    }

    @Test
    public void testDoGetValidProduct() throws ServletException, IOException {
        when(productLoader.loadProductFromURL(request)).thenReturn(product);
        servlet.doGet(request, response);

        verify(request).setAttribute("product", product);
        verify(requestDispatcher).forward(request, response);
    }

}