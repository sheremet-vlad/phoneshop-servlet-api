package com.es.phoneshop.dao.deliveryModeDao;

import com.es.phoneshop.model.deliveryMode.DeliveryMode;

import java.util.List;

public class ArrayListDeliveryModeDao<T extends DeliveryMode> extends DeliveryModeDao<T> {

    private static volatile ArrayListDeliveryModeDao<DeliveryMode> instance;
    private static final Object lock = new Object();

    private ArrayListDeliveryModeDao() {

    }

    public static DeliveryModeDao getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ArrayListDeliveryModeDao<>();
                }
            }
        }

        return instance;
    }

    @Override
    public List<T> findAllEntities() {
        return entities;
    }
}
