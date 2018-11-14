package com.es.phoneshop.model.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private final List<Product> productList = new ArrayList<>();

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
    public Product getProduct(Long id) {
        synchronized (productList) {
            return productList.stream()
                    .filter((p) -> p.getId().equals(id))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("There is no element with such id = " + id));
        }
    }

    @Override
    public List<Product> findProducts() {
        synchronized (productList) {
            return productList.stream()
                    .filter((p) -> p.getPrice() != null && p.getStock() > 0)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void save(Product product) {
        Long id = product.getId();

        synchronized (productList) {
            boolean isExist = productList.stream()
                    .anyMatch((p) -> p.getId().equals(id));

            if (!isExist) {
                productList.add(product);
            }
        }
    }

    @Override
    public void delete(Long id) {
        synchronized (productList) {
            productList.remove(getProduct(id));
        }
    }
}
