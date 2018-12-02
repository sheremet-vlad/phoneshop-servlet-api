package com.es.phoneshop.model.viwedProduct;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.viewedProduct.ViewedProductList;
import com.es.phoneshop.model.viewedProduct.ViewedProductService;
import com.es.phoneshop.model.viewedProduct.ViewedProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ViewedProductServiceImplTest {
    private final static ViewedProductService viewedProductService = ViewedProductServiceImpl.getInstance();
    private static ViewedProductList viewedProductList;
    private static List<Product> products;

    @Before
    public void setup() {
        viewedProductList = new ViewedProductList();
        products = new ArrayList<>();
        products.add(new Product(1L, "sgs1", "Sama", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        products.add(new Product(2L, "sgs2", "Sama", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        products.add(new Product(3L, "sgs3", "Sama", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        products.add(new Product(4L, "sgs4", "Sama", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        products.add(new Product(5L, "sgs5", "Sama", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
    }

    @Test
    public void testAddToViewedProducts() {
        viewedProductService.addToViewed(products.get(0), viewedProductList);

        int expectedSize = 1;
        int actualSize = viewedProductList.getViewedProduct().size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testAddFiveProductsToViewedProducts() {
        viewedProductService.addToViewed(products.get(0), viewedProductList);
        viewedProductService.addToViewed(products.get(1), viewedProductList);
        viewedProductService.addToViewed(products.get(2), viewedProductList);
        viewedProductService.addToViewed(products.get(3), viewedProductList);
        viewedProductService.addToViewed(products.get(4), viewedProductList);


        int expectedSize = 3;
        int actualSize = viewedProductList.getViewedProduct().size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testRefreshProductIndexInViewedProducts() {
        Product product = products.get(0);

        viewedProductService.addToViewed(product, viewedProductList);
        viewedProductService.addToViewed(products.get(1), viewedProductList);
        viewedProductService.addToViewed(products.get(2), viewedProductList);

        viewedProductService.addToViewed(product, viewedProductList);
        List<Product> viewedProducts = viewedProductList.getViewedProduct();

        int expectedIndex = 0;
        int actualIndex = -1;

        for (int i = 0; i < viewedProducts.size(); i++) {
            if (viewedProducts.get(i).getId().equals(product.getId())) {
                actualIndex = i;
                break;
            }
        }

        Assert.assertEquals(expectedIndex, actualIndex);
    }




}
