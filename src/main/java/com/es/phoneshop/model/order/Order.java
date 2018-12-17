package com.es.phoneshop.model.order;

import com.es.phoneshop.model.cart.Cart;

import java.util.Objects;

/**
 * This class contain
 * cart attribute and order
 * owner information.
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public class Order extends Cart {
    /**this id added to url, that make him safe*/
    private String secureId;

    /**Name of order owner*/
    private String name;

    /**Address to delivery order*/
    private String deliveryAddress;

    /**Phone of order owner*/
    private String phone;

    /**
     * Method return order secure id
     *
     * @return {@code String} order secure id
     */
    public String getSecureId() {
        return secureId;
    }

    /**
     * Method sets passed parameter to order
     * secure id
     *
     * @param secureId this parameter will be set to order secure id
     */
    public void setSecureId(String secureId) {
        this.secureId = secureId;
    }

    /**
     * Method returns order owner name
     *
     * @return {@code String} order owner name
     */
    public String getName() {
        return name;
    }

    /**
     * Method sets passed parameter to order
     * owner name
     *
     * @param name this parameter will be set to order owner name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method returns order delivery address
     *
     * @return {@code String} order owner name
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Method sets passed parameter to order
     * delivery address
     *
     * @param deliveryAddress this parameter will be set to order
     *  delivery address
     */
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * Method returns order owner phone
     *
     * @return {@code String} order owner phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Method sets passed parameter to order
     * owner phone
     *
     * @param phone this parameter will be set to order owner phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + super.getId() +
                ", secureId='" + secureId + '\'' +
                ", name='" + name + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(secureId, order.secureId) &&
                Objects.equals(name, order.name) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(phone, order.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), secureId, name, deliveryAddress, phone);
    }
}
