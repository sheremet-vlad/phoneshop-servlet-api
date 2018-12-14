package com.es.phoneshop.dao.orderDao;

public class ArrayListOrderDao extends OrderDao {

    private static volatile ArrayListOrderDao arrayListOrderDao = null;

    private static final Object lock = new Object();

    private ArrayListOrderDao() {
    }

    public static ArrayListOrderDao getInstance() {
        if (arrayListOrderDao == null) {
            synchronized (lock) {
                if (arrayListOrderDao == null) {
                    arrayListOrderDao = new ArrayListOrderDao();
                }
            }
        }
        return arrayListOrderDao;
    }
}
