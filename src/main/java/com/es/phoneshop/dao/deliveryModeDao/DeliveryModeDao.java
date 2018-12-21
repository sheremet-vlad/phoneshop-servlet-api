package com.es.phoneshop.dao.deliveryModeDao;

import com.es.phoneshop.dao.DaoImpl;
import com.es.phoneshop.model.deliveryMode.DeliveryMode;

import java.util.List;

public abstract class DeliveryModeDao<T extends DeliveryMode> extends DaoImpl<T> {
    public abstract List<T> findAllEntities();
}
