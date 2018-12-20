package com.es.phoneshop.model.product;

import com.es.phoneshop.model.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Product extends Entity {
    /**can't be null, product code*/
    private String code;

    /**can't be null, product description*/
    private String description;

    /** null means there is no price because the product is outdated or new */
    private BigDecimal price;

    /** can be null if the price is null */
    private Currency currency;

    /** can be null, count of product*/
    private int stock;

    /**contain main photo url of product */
    private String imageUrl;

    /**
     * Method creates object and sets nothing
     */
    public Product() {
    }

    /**
     * Method creates object and set passed parameters
     *
     * @param id product id
     * @param code product code, producer name
     * @param description product description
     * @param price product cost, can be null
     * @param currency price currency
     * @param stock product stock, can be null
     * @param imageUrl main photo url
     */
    public Product(Long id, String code, String description, BigDecimal price, Currency currency, int stock, String imageUrl) {
        super(id);
        this.code = code;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    /**
     * Method returns product code
     *
     * @return {@code String} product code, producer name
     */
    public String getCode() {
        return code;
    }

    /**
    / * Method sets code to product
     *
     * @param code code that will be set to product code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Method returns product description
     *
     * @return {@code String} product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method sets product description
     *
     * @param description description that will be set to product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method gets product price
     *
     * @return {@code BigDecimal} product price, null means
     * there is no price because the product is outdated or new
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Method sets product price
     *
     * @param price price that will be st to product price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Method gets product currency
     *
     * @return @{code Currency} product currency, can be null
     * if price is null
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Method sets product currency
     *
     * @param currency currency that will be set to product currency
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Method gets product stock
     *
     * @return {@code int} product stock, can be null
     * if  product is outdated
     */
    public int getStock() {
        return stock;
    }

    /**
     * Method sets product stock
     * You can set null that mean
     * what product is outed
     *
     * @param stock product stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Method returns main photo url
     *
     * @return {@code String} main photo url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Method sets main photo url
     *
     * @param imageUrl url that will be set to product url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                ", stock=" + stock +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return stock == product.stock &&
                Objects.equals(code, product.code) &&
                Objects.equals(description, product.description) &&
                Objects.equals(price, product.price) &&
                Objects.equals(currency, product.currency) &&
                Objects.equals(imageUrl, product.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, description, price, currency, stock, imageUrl);
    }
}