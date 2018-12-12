package com.es.phoneshop.web.filter;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.util.ProductLoader;
import com.es.phoneshop.model.viewedProduct.ViewedProductList;
import com.es.phoneshop.model.viewedProduct.ViewedProductService;
import com.es.phoneshop.model.viewedProduct.ViewedProductServiceImpl;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VeiwedProductsFilter implements Filter {

    private ViewedProductService viewedProductService;
    private ProductLoader productLoader;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        viewedProductService = ViewedProductServiceImpl.getInstance();
        productLoader = ProductLoader.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            Product product = productLoader.loadProductFromURL(request);
            HttpSession httpSession = request.getSession();
            ViewedProductList viewedProductList = viewedProductService.getViewedProductList(httpSession);
            viewedProductService.addToViewed(product, viewedProductList);
        } catch (IllegalArgumentException e){
            throw new ServletException(e.getMessage());
        }

        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
