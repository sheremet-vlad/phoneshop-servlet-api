package com.es.phoneshop.service.orderService;

import com.es.phoneshop.dao.orderDao.ArrayListOrderDao;
import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartItem;
import com.es.phoneshop.model.order.Order;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.service.cartService.CartService;
import com.es.phoneshop.service.cartService.CartServiceImpl;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderServiceImpl implements OrderService {

    private static volatile OrderService orderService;
    private static final Object lock = new Object();

    private OrderServiceImpl() {

    }

    public static OrderService getInstance() {
        if (orderService == null) {
            synchronized (lock) {
                if (orderService == null) {
                    orderService = new OrderServiceImpl();
                }
            }
        }

        return orderService;
    }

    @Override
    public Order placeOrder(Cart cart, String name, String deliveryAddress, String phone) {
        Order order = new Order();
        order.setName(name);
        order.setDeliveryAddress(deliveryAddress);
        order.setPhone(phone);
        order.getCartItems().addAll(cart.getCartItems());
        order.setTotalPrice(cart.getTotalPrice());
        ArrayListOrderDao.getInstance().save(order);
        return order;
    }
}
