package com.es.phoneshop.dao.product;

import com.es.phoneshop.dao.productDao.ArrayListProductDao;
import com.es.phoneshop.dao.productDao.ProductDao;
import com.es.phoneshop.model.product.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ArrayListProductDaoTest
{
    private ProductDao<Product> productDao;
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

        Long id = productDao.getEntity(numId).getId();

        Assert.assertEquals(1, id.longValue());
    }

    @Test
    public void findAllProducts() {
        List<Product> actualList = productDao.findEntities("",null, null);
        int expectedSize = 7;

        Assert.assertEquals(expectedSize, actualList.size());
    }

    @Test
    public void saveProduct() {
        long id = 8L;
        productDao.save(new Product(8L, "sga", "Sss", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https"));
        Product prod = productDao.getEntity(id);

        Assert.assertNotNull(prod);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeProduct() {
        long id = 8L;

        productDao.delete(id);
        productDao.getEntity(id);
    }

    @Test
    public void findOneProduct() {
        String query = "no";
        int expectedSize = 1;

        List actualList= productDao.findEntities(query,null, null);
        int actualSize = actualList.size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void returnThreeProduct() {
        String query = "Sa or no";
        int expectedSize = 3;

        List actualList = productDao.findEntities(query,null, null);
        int actualSize = actualList.size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void returnAllProduct() {
        int expectedSize = 6;

        List actualList = productDao.findEntities(null,null, null);
        int actualSize = actualList.size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void returnAscSortedByPriceProducts() {
        List<Product> actualList = productDao.findEntities(null,"asc", "price");

        boolean sorted = true;
        for(int i = 1; i < actualList.size(); i++) {
            if(actualList.get(i - 1).getPrice().compareTo(actualList.get(i).getPrice()) < 0){
                sorted = false;
                break;
            }
        }

        Assert.assertTrue(sorted);
    }

    @Test
    public void returnDecSortedByPriceProducts() {
        List<Product> actualList = productDao.findEntities(null,"dec", "price");

        boolean sorted = true;
        for(int i = 1; i < actualList.size(); i++) {
            if(actualList.get(i - 1).getPrice().compareTo(actualList.get(i).getPrice()) > 0){
                sorted = false;
                break;
            }
        }

        Assert.assertTrue(sorted);
    }

    @Test
    public void returnAscSortedByDescriptionProducts() {
        List<Product> actualList = productDao.findEntities(null,"asc", "description");

        boolean sorted = true;
        for(int i = 1; i < actualList.size(); i++) {
            if(actualList.get(i - 1).getDescription().compareTo(actualList.get(i).getDescription()) > 0){
                sorted = false;
                break;
            }
        }

        Assert.assertTrue(sorted);
    }

    @Test
    public void returnDecSortedByDescriptionProducts() {
        List<Product> actualList = productDao.findEntities(null,"dec", "description");

        boolean sorted = true;
        for(int i = 1; i < actualList.size(); i++) {
            if(actualList.get(i - 1).getDescription().compareTo(actualList.get(i).getDescription()) < 0){
                sorted = false;
                break;
            }
        }

        Assert.assertTrue(sorted);
    }
}
