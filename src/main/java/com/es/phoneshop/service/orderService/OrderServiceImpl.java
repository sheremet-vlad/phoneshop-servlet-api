package com.es.phoneshop.service.orderService;

import com.es.phoneshop.dao.orderDao.ArrayListOrderDao;
import com.es.phoneshop.dao.orderDao.OrderDao;
import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartItem;
import com.es.phoneshop.model.order.Order;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public class OrderServiceImpl implements OrderService {

    private OrderDao<Order> orderDao = ArrayListOrderDao.getInstance();

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
        order.setSecureId(UUID.randomUUID().toString());
        List<CartItem> cartListItems = cart.getCartItems();
        order.setCartItems(cartListItems.stream().map(CartItem::new).collect(toList()));
        order.setTotalPrice(cart.getTotalPrice());

        orderDao.save(order);

        return order;
    }

    @Override
    public boolean checkParameters(String name, String deliveryAddress, String phone) {
        return (name != null && deliveryAddress != null && phone != null) &&
                !(name.equals("") || deliveryAddress.equals("") || phone.equals(""));
    }
}
