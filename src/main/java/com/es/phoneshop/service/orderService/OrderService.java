package com.es.phoneshop.service.orderService;

import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.order.Order;

/**
 * This interface imposes method
 * to work with order
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public interface OrderService {
    /**
     * Method creates order from cart and
     * order owner parameter such, as name,
     * delivery address and phone
     *
     * @param cart from this cart takes list of
     *             cart items and total price of items
     * @param name order owner name
     * @param deliveryAddress order delivery address
     * @param phone order owner phone
     * @param lastName order owner last name
     *
     * @return {@code Order} returns order with sets
     *  passed parameters
     */
    Order placeOrder(Cart cart, String name, String lastName, String deliveryAddress, String phone, String deliveryModeId);

    /**
     * Method check order owner parameters
     *
     * @param name order owner name
     * @param deliveryAddress order delivery address
     * @param phone order owner phone
     * @param lastName order owner last name
     *
     * @return {@code boolean} if all passed parameters
     * not null and not empty then return true, else false
     */
    boolean checkParameters(String name, String lastName, String deliveryAddress, String phone);
}
