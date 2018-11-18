package com.es.phoneshop.model.product;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ArrayListProductDaoTest
{
    private ProductDao productDao;
    private List<Product> list;

    @Before
    public void setup() {
        productDao = ArrayListProductDao.getInstance();
        list = new ArrayList<>();

        list.add(new Product(1L, "sgs", "Sama", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        list.add(new Product(2L, "sgt", "Sa", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        list.add(new Product(3L, "sgy", "Sdsv", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        list.add(new Product(4L, "sgh", "hi", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        list.add(new Product(5L, "sgj", "no", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        list.add(new Product(6L, "sgb", "yes", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));

        list.forEach(productDao::save);
    }

    @Test
    public void getInstance() {
        ArrayListProductDao instance = ArrayListProductDao.getInstance();

        Assert.assertNotNull(instance);
    }

    @Test
    public void getOneProductWithIdOne() {
        long numId = 1L;

        Long id = productDao.getProduct(numId).getId();

        Assert.assertEquals(1, id.longValue());
    }

    @Test
    public void findAllProducts() {
        List<Product> actualList = productDao.findProducts("",null, null);
        int expectedSize = 7;

        Assert.assertEquals(expectedSize, actualList.size());
    }

    @Test
    public void saveProduct() {
        long id = 8L;
        productDao.save(new Product(8L, "sga", "Sss", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        Product prod = productDao.getProduct(id);

        Assert.assertNotNull(prod);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeProduct() {
        long id = 8L;

        productDao.delete(id);
        productDao.getProduct(id);
    }

    @Test
    public void findOneProduct() {
        String query = "no";
        int expectedSize = 1;

        List actualList= productDao.findProducts(query,null, null);
        int actualSize = actualList.size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void returnThreeProduct() {
        String query = "Sa or no";
        int expectedSize = 3;

        List actualList = productDao.findProducts(query,null, null);
        int actualSize = actualList.size();

        Assert.assertEquals(expectedSize, actualSize);
    }
    
}
