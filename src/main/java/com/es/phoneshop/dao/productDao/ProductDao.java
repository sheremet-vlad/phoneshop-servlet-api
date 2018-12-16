package com.es.phoneshop.dao.productDao;

import com.es.phoneshop.dao.DaoImpl;
import com.es.phoneshop.model.product.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class ProductDao<T extends Product> extends DaoImpl<T> {
    public abstract List<T> findEntities(String query, String order, String sort);
}
