package com.es.phoneshop.dao.order;

import com.es.phoneshop.dao.orderDao.ArrayListOrderDao;
import com.es.phoneshop.dao.orderDao.OrderDao;
import com.es.phoneshop.dao.productDao.ArrayListProductDao;
import com.es.phoneshop.model.cart.CartItem;
import com.es.phoneshop.model.order.Order;
import com.es.phoneshop.model.product.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayListOrderDaoTest {
    private final static String SECURE_ID = "aaabbbccc";
    private final static OrderDao<Order> orderDao = ArrayListOrderDao.getInstance();
    private Order order = new Order();

    @Before
    public void setup() {
        order.setName("a");
        order.setDeliveryAddress("b");
        order.setPhone("c");
        order.setSecureId(SECURE_ID);
        List<CartItem> list = new ArrayList<>();
        list.add(new CartItem(new Product(), 10));
        order.setCartItems(list);
        orderDao.save(order);
    }

    @Test
    public void getInstance() {
        ArrayListProductDao instance = ArrayListProductDao.getInstance();

        Assert.assertNotNull(instance);
    }

    @Test
    public void testFindEntity() {
        String expectedSecureId = SECURE_ID;

        Order order = orderDao.getEntity(SECURE_ID);
        String actualSecureId = order.getSecureId();

        Assert.assertEquals(expectedSecureId, actualSecureId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeProduct() {
        orderDao.getEntity("casdfvf");
    }
}
