package com.es.phoneshop.service.orderService;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartItem;
import com.es.phoneshop.model.order.Order;
import com.es.phoneshop.model.product.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImplTest {
    private Cart cart;

    private final static OrderService orderService = OrderServiceImpl.getInstance();
    private Order order;

    @Before
    public void setup() {
        cart = new Cart();
        List<CartItem> list = new ArrayList<>();
        list.add(new CartItem(new Product(), 4));
        cart.setCartItems(list);
        cart.setTotalPrice(new BigDecimal(100));
    }

    @Test
    public void testPlaceOrder() {
        order = orderService.placeOrder(cart, "a", "f","b", "c");
        String actualName = order.getName();
        String expectedName = "a";

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testCheckParameters() {
        boolean result = orderService.checkParameters("a","g","b", "c");

        Assert.assertTrue(result);
    }

    @Test
    public void testCheckParametersWithEmptyField() {
        boolean result = orderService.checkParameters("","f","b", "c");

        Assert.assertFalse(result);
    }

    @Test
    public void testCheckParametersWithNullParameter() {
        boolean result = orderService.checkParameters(null,"b","b", "c");

        Assert.assertFalse(result);
    }


}
