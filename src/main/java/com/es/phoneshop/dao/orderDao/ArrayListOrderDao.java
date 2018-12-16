package com.es.phoneshop.dao.orderDao;

import com.es.phoneshop.model.order.Order;

public class ArrayListOrderDao<T extends Order> extends OrderDao<T> {

    private static volatile ArrayListOrderDao<Order> arrayListOrderDao = null;

    private static final Object lock = new Object();

    private ArrayListOrderDao() {
    }

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
    public T getEntity(String id) {
        synchronized (entities) {
            return entities.stream()
                    .filter((p) -> p.getSecureId().equals(id))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("There is no element with such id = " + id));
        }
    }
}
