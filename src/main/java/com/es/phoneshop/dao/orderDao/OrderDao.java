package com.es.phoneshop.dao.orderDao;

import com.es.phoneshop.dao.DaoImpl;
import com.es.phoneshop.model.order.Order;

public abstract class OrderDao<T extends Order> extends DaoImpl<T> {
    public abstract T getEntity(String id);
}
