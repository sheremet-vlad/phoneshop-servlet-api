package com.es.phoneshop.dao.productDao;

import com.es.phoneshop.model.product.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArrayListProductDao<T extends Product> extends ProductDao<T> {
    private final static String QUERY_SPLIT = "\\s";
    private final static String SORT_DESCRIPTION = "description";
    private final static String ORDER_DEC = "dec";

    private static volatile ArrayListProductDao<Product> arrayListProductDao = null;

    private static final Object lock = new Object();

    private ArrayListProductDao() {
    }

    public static ArrayListProductDao<Product> getInstance() {
        if (arrayListProductDao == null) {
            synchronized (lock) {
                if (arrayListProductDao == null) {
                    arrayListProductDao = new ArrayListProductDao<>();
                }
            }
        }
        return arrayListProductDao;
    }

    @Override
    public List<T> findEntities(String query, String order, String sort) {

        synchronized (entities) {
            Predicate<Product> isValidProduct = product -> product.getPrice() != null && product.getStock() > 0;

            List<T> foundProduct = entities.stream()
                    .filter(isValidProduct)
                    .collect(Collectors.toList());

            if (query != null) {
                String[] queries = query.split(QUERY_SPLIT);

                foundProduct = entities.stream()
                        .collect(Collectors.toMap(Function.identity(), product -> Arrays.stream(queries)
                                .filter(word -> product.getDescription().contains(word)).count()))
                        .entrySet().stream()
                        .filter(entry -> entry.getValue() > 0)
                        .sorted(Comparator.comparing(Map.Entry<T, Long>::getValue).reversed())
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
            }

            if (sort != null) {
                return sortProduct(foundProduct, order, sort);
            } else {
                return foundProduct;
            }
        }
    }

    private List<T> sortProduct(List<T> list, String order, String sort) {
        Comparator<Product> comparator;

        if (sort.equals(SORT_DESCRIPTION)) {
            comparator = Comparator.comparing(Product::getDescription);
        } else {
            comparator = Comparator.comparing(Product::getPrice);
        }

        if (order.equals(ORDER_DEC)) {
            comparator = (comparator).reversed();
        }

        return list.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }


}
