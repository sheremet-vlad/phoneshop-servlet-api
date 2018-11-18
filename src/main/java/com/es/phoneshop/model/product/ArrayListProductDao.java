package com.es.phoneshop.model.product;

import com.es.phoneshop.model.enumeration.OrderEnum;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private final static String QUERY_SPLIT = " or ";
    private final static String SORT_DESCRIPTION = "description";

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
    public List<Product> findProducts(String query, String order, String sort) {
        synchronized (productList) {
            String[] queries = query == null ? null : query.split(QUERY_SPLIT);

            List<Product> foundProduct = productList.stream()
                    .filter((p) -> p.getPrice() != null && p.getStock() > 0)
                    .filter(p -> query == null || Arrays.stream(queries).anyMatch((q) -> p.getDescription().contains(q)))
                    .collect(Collectors.toList());

            if (order != null && sort != null) {
                return sortProduct(foundProduct, order, sort);
            }
            else {
                return foundProduct;
            }
        }
    }

    private List<Product> sortProduct(List<Product> list, String order, String sort) {
        OrderEnum orderEnum = OrderEnum.valueOf(order.toUpperCase());
        List<Product> resultList;

        if (sort.equals(SORT_DESCRIPTION)) {
            resultList = list.stream()
                    .sorted(Comparator.comparing(Product::getDescription))
                    .collect(Collectors.toList());
        }
        else {
            resultList = list.stream()
                    .sorted(Comparator.comparing(Product::getPrice))
                    .collect(Collectors.toList());
        }

        if (orderEnum == OrderEnum.DEC) {
            Collections.reverse(resultList);
        }

        return resultList;
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
