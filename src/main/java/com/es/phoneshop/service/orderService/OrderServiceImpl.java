package com.es.phoneshop.service.orderService;

import com.es.phoneshop.dao.orderDao.ArrayListOrderDao;
import com.es.phoneshop.dao.orderDao.OrderDao;
import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.cart.CartItem;
import com.es.phoneshop.model.order.Order;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

/**
 * This class realize interface
 * to work with order. It is a singleton.
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {

    /** used to save order in dao*/
    private OrderDao<Order> orderDao = ArrayListOrderDao.getInstance();

    /** contain instance of OrderServiceImpl*/
    private static volatile OrderService orderService;

    /** used to Double-Checked Locking singleton*/
    private static final Object lock = new Object();

    /** Private method creates object */
    private OrderServiceImpl() {
    }

    /**
     * Method realizes singleton pattern.
     * If orderService is null, then method creates new
     * orderServiceImpl. Returns instance of OrderServiceImpl.
     *
     * @return {@code OrderService} instance of OrderServiceImpl.
     */
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
