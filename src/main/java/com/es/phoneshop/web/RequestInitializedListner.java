package com.es.phoneshop.web;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;
import com.es.phoneshop.model.viewedProduct.ViewedProductList;
import com.es.phoneshop.model.viewedProduct.ViewedProductService;
import com.es.phoneshop.model.viewedProduct.ViewedProductServiceImpl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RequestInitializedListner implements ServletRequestListener {
    private ProductDao productDao;
    private ViewedProductService viewedProductService;

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        productDao = ArrayListProductDao.getInstance();
        viewedProductService = ViewedProductServiceImpl.getInstance();

        try {
            Product product = loadProduct(request);
            HttpSession httpSession = request.getSession();
            ViewedProductList viewedProductList = viewedProductService.getViewedProductList(httpSession);
            viewedProductService.addToViewed(product, viewedProductList);
        } catch (Exception ignored){

        }

    }

    private Product loadProduct(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int indexOfIdInUri = request.getContextPath().length() + request.getServletPath().length() + 1;
        Long id = Long.parseLong(uri.substring(indexOfIdInUri));
        return productDao.getProduct(id);
    }
}
