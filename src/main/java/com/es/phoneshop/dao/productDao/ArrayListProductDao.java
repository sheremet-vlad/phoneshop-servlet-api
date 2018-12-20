package com.es.phoneshop.dao.productDao;

import com.es.phoneshop.model.product.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class realize {@link ProductDao}
 * Class work with objects, that extends
 * {@link Product}. It is a singleton.
 *
 * @param <T> type of object with which work database.
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public class ArrayListProductDao<T extends Product> extends ProductDao<T> {

    /** expression to split query */
    private final static String QUERY_SPLIT = "\\s";

    /** parameter to sort list by description */
    private final static String SORT_DESCRIPTION = "description";

    /** descending sorting direction */
    private final static String ORDER_DEC = "dec";

    /** contain instance of ArrayListProductDao*/
    private static volatile ArrayListProductDao<Product> arrayListProductDao = null;

    /** used to Double-Checked Locking singleton*/
    private static final Object lock = new Object();

    /** Private method creates object*/
    private ArrayListProductDao() {
    }

    /**
     * Method realizes singleton pattern.
     * If ArrayListProductDao is null, then method creates new
     * arrayListProductDao. Returns instance of ArrayListProductDao.
     *
     * @return {@code ArrayListProductDao} instance of ArrayListProductDao
     */
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

    /**
     * Method sorts list by sort-parameter. If sort is null,
     * then takes default argument (price) to sorting. List will
     * by sort by order. If order is null, then takes default
     * value(asc).
     *
     * @param list list to sort
     * @param order sorting order
     * @param sort soring by this parameter
     *
     * @return {@code List} sorted list
     */
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
