package com.es.phoneshop.service.cartService;

import com.es.phoneshop.exception.IllegalStockArgumentException;
import com.es.phoneshop.model.cart.Cart;
import com.es.phoneshop.model.product.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class CartServiceImplTest {

    private final static CartService cartService = CartServiceImpl.getInstance();
    private static Cart cart;
    private static Product product;
    private static Product product1;

    @Before
    public void setup() {
        cart = new Cart();
        product = new Product(1L, "sgs", "Sama", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https");
        product1 = new Product(2L, "sgs", "Sama", new BigDecimal(100), Currency.getInstance(Locale.US), 100, "https");
    }

    @Test
    public void testAddCartItemToCart() throws IllegalStockArgumentException {
        cartService.addToCart(cart,product,1);

        List items = cart.getCartItems();

        int expectedSize = 1;
        int actualSize = items.size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testAddTwoItemsToCart() throws IllegalStockArgumentException {
        cartService.addToCart(cart,product,1);
        cartService.addToCart(cart,product1,2);

        List items = cart.getCartItems();

        int expectedSize = 2;
        int actualSize = items.size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = IllegalStockArgumentException.class)
    public void shouldReturnExceptionWhenQuantityMoreThenStock() throws IllegalStockArgumentException {
        cartService.addToCart(cart,product,1000);
    }

    @Test
    public void testDeleteProduct() throws IllegalStockArgumentException{
        cartService.addToCart(cart, product, 1);
        cartService.addToCart(cart, product1, 2);

        cartService.delete(cart, product);

        int expectedSize = 1;
        int actualSize = cart.getCartItems().size();

        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testUpdateProductQuantity() throws IllegalStockArgumentException{
        cartService.addToCart(cart, product, 1);

        cartService.updateCart(cart, product, 2);

        int expectedQuantity = 2;
        int actualQuantity = cart.getCartItems().get(0).getQuantity();

        Assert.assertEquals(expectedQuantity, actualQuantity);
    }

    @Test(expected = IllegalStockArgumentException.class)
    public void shouldReturnExceptionWhenUpdatingQuantityMoreThenStock() throws IllegalStockArgumentException {
        cartService.addToCart(cart,product,1000);
    }

    @Test(expected = IllegalStockArgumentException.class)
    public void shouldReturnExceptionWhenUpdatingQuantityIsNull() throws IllegalStockArgumentException {
        cartService.addToCart(cart,product,null);
    }

    @Test
    public void testCalculateTotalPrice() throws IllegalStockArgumentException{
        cartService.addToCart(cart, product1, 1);
        cartService.addToCart(cart, product, 2);

        BigDecimal expectedPrice = new BigDecimal(300);
        BigDecimal actualPrice = cart.getTotalPrice();

        Assert.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testClearCart() throws IllegalStockArgumentException{
        cartService.addToCart(cart, product, 1);

        cartService.clearCart(cart);

        int expectedSize = 0;
        int actualSize = cart.getCartItems().size();

        Assert.assertEquals(expectedSize, actualSize);
    }
}
