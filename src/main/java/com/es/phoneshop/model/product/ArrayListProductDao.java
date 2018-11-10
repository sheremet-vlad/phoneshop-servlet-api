package com.es.phoneshop.model.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private List<Product> productList = new ArrayList<>();

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

    @Override
    public synchronized Product getProduct(Long id) {
        return productList.stream()
                .filter((p) -> p.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no element with such id"));
    }

    @Override
    public synchronized List<Product> findProducts() {
        return productList.stream()
                .filter((p) -> p.getPrice().compareTo(BigDecimal.ZERO) > 0 && p.getStock() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public synchronized void save(Product product) {
        Long id = product.getId();
        boolean isExist = productList.stream()
                .anyMatch((p) -> p.getId().equals(id));

        if (!isExist) {
            productList.add(product);
        }
    }

    @Override
    public synchronized void delete(Long id) {
        productList.remove(getProduct(id));
    }

    @Override
    public synchronized void saveAll(List<Product> products) {
        products.forEach(this::save);
    }
}
