package com.es.phoneshop.web.filter;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.viewedProduct.ViewedProductList;
import com.es.phoneshop.service.cartService.CartService;
import com.es.phoneshop.service.cartService.CartServiceImpl;
import com.es.phoneshop.service.viewedProductService.ViewedProductService;
import com.es.phoneshop.service.viewedProductService.ViewedProductServiceImpl;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AttributeSetterFilter implements Filter {
    private final static String CART_ATTRIBUTE = "cart";
    private final static String VIEWED_PRODUCT_ATTRIBUTE = "viewedProducts";

    private CartService cartService;
    private ViewedProductService viewedProductService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        cartService = CartServiceImpl.getInstance();
        viewedProductService = ViewedProductServiceImpl.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        servletRequest.setAttribute(CART_ATTRIBUTE, cartService.getCart(session));

        List<Product> viewedList = viewedProductService.getViewedProductList(session).getViewedProduct();
        servletRequest.setAttribute(VIEWED_PRODUCT_ATTRIBUTE, viewedList);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
