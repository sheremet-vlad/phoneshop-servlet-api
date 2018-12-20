package com.es.phoneshop.dao.orderDao;

import com.es.phoneshop.model.order.Order;

/**
 * This class realize {@link OrderDao}
 * Class work with objects, that extends
 * {@link Order}. It is a singleton.
 *
 * @param <T> type of object with which work database.
 * @author sheremet-vlad
 * @version 1.0
 */
public class ArrayListOrderDao<T extends Order> extends OrderDao<T> {

    /** contain instance of ArrayListOrderDao*/
    private static volatile ArrayListOrderDao<Order> arrayListOrderDao = null;

    /** used to Double-Checked Locking singleton*/
    private static final Object lock = new Object();

    /** Private method creates object*/
    private ArrayListOrderDao() {
    }

    /**
     * Method realizes singleton pattern.
     * If ArrayListOrderDao is null, then method creates new
     * arrayListOrderDao. Returns instance of ArrayListOrderDao.
     *
     * @return {@code ArrayListOrderDao} instance of ArrayListOrderDao
     */
    public static ArrayListOrderDao<Order> getInstance() {
        if (arrayListOrderDao == null) {
            synchronized (lock) {
                if (arrayListOrderDao == null) {
                    arrayListOrderDao = new ArrayListOrderDao<>();
                }
            }
        }
        return arrayListOrderDao;
    }

    @Override
    public T getEntity(String secureId) {
        synchronized (entities) {
            return entities.stream()
                    .filter((p) -> secureId.equals(p.getSecureId()))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("There is no element with such id = " + secureId));
        }
    }
}
