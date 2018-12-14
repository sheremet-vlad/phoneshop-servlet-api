package com.es.phoneshop.dao.productDao;

import com.es.phoneshop.model.product.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArrayListProductDao extends ProductDao {

    private static volatile ArrayListProductDao arrayListProductDao = null;

    private static final Object lock = new Object();

    private ArrayListProductDao() {
    }

    public static ArrayListProductDao getInstance() {
        if (arrayListProductDao == null) {
            synchronized (lock) {
                if (arrayListProductDao == null) {
                    arrayListProductDao = new ArrayListProductDao();
                }
            }
        }
        return arrayListProductDao;
    }


}
