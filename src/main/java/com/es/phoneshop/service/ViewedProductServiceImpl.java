package com.es.phoneshop.service;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.viewedProduct.ViewedProductList;

import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewedProductServiceImpl implements ViewedProductService {

    private final static String VIEWED_LIST_PARAMETER = "recentlyViewedProduct";
    private final static int SIZE_OF_VIEWED_LIST = 3;

    private static volatile ViewedProductService viewedProductService;
    private final static Object lock = new Object();

    private ViewedProductServiceImpl() {

    }

    public static ViewedProductService getInstance() {
        if (viewedProductService == null) {
            synchronized (lock) {
                if (viewedProductService == null) {
                    viewedProductService = new ViewedProductServiceImpl();
                }
            }
        }

        return viewedProductService;
    }

    @Override
    public ViewedProductList getViewedProductList(HttpSession session) {
        ViewedProductList viewedProducts = (ViewedProductList) session.getAttribute(VIEWED_LIST_PARAMETER);

        if (viewedProducts == null) {
            viewedProducts = new ViewedProductList();
            session.setAttribute(VIEWED_LIST_PARAMETER, viewedProducts);
        }

        return viewedProducts;
    }

    @Override
    public void addToViewed(Product product, ViewedProductList viewedProductList) {
        List<Product> productList = viewedProductList.getViewedProduct();
        Long id = product.getId();

        boolean isProductWasInList = productList.removeIf(product1 -> product1.getId().equals(id));

        if (!isProductWasInList && productList.size() == SIZE_OF_VIEWED_LIST) {
            productList.remove(productList.size() - 1);
        }

        productList.add(0, product);
    }
}
