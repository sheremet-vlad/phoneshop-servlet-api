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
    private static Cart cart;

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
        order = orderService.placeOrder(cart, "a", "b", "c");
        Long actualId = order.getId();
        Long expectedId = 0L;

        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void testCheckParameters() {
        boolean result = orderService.checkParameters("a","b", "c");

        Assert.assertTrue(result);
    }

    @Test
    public void testCheckParametersWithEmptyField() {
        boolean result = orderService.checkParameters("","b", "c");

        Assert.assertFalse(result);
    }

    @Test
    public void testCheckParametersWithNullParameter() {
        boolean result = orderService.checkParameters(null,"b", "c");

        Assert.assertFalse(result);
    }


}
