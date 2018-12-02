package com.es.phoneshop.model.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private final static String QUERY_SPLIT = "\\s";
    private final static String SORT_DESCRIPTION = "description";
    private final static String SORT_PRICE = "price";
    private final static String ORDER_DEC = "dec";


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
            List<Product> foundProduct = productList.stream()
                    .filter((p) -> p.getPrice() != null && p.getStock() > 0)
                    .collect(Collectors.toList());

            if (query != null) {
                String[] queries = query.split(QUERY_SPLIT);
                foundProduct = productList.stream()
                        .filter(p -> Arrays.stream(queries).anyMatch((q) -> p.getDescription().contains(q)))
                        .sorted((p, q) -> {
                            Long s1 = Arrays.stream(queries).filter(word -> p.getDescription().contains(word)).count();
                            Long s2 = Arrays.stream(queries).filter(word -> q.getDescription().contains(word)).count();
                            return s2.compareTo(s1);
                        })
                        .collect(Collectors.toList());
            }

            if (order != null && sort != null) {
                return sortProduct(foundProduct, order, sort);
            } else {
                return foundProduct;
            }
        }
    }

    @Override
    public void save(Product product) {
        synchronized (productList) {
            if (!isExist(product.getId())) {
                productList.add(product);
            }
        }
    }

    @Override
    public void delete(Long id) {
        synchronized (productList) {
            if (!productList.removeIf(p -> p.getId().equals(id))) {
                throw new IllegalArgumentException("There is no element with such id = " + id);
            }
        }
    }

    private boolean isExist(Long id) {
        return productList.stream()
                .anyMatch((p) -> p.getId().equals(id));
    }

    private List<Product> sortProduct(List<Product> list, String order, String sort) {
        Comparator<Product> comparator;

        if (sort.equals(SORT_DESCRIPTION)) {
            comparator = Comparator.comparing(Product::getDescription);
        } else if (sort.equals(SORT_PRICE)) {
            comparator = Comparator.comparing(Product::getPrice);
        } else {
            return list;
        }

        if (order.equals(ORDER_DEC)) {
            comparator = (comparator).reversed();
        }

        return list.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        synchronized (productList) {
            productList.clear();
        }
    }
}
