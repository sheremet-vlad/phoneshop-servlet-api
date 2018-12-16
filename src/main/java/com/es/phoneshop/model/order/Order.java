package com.es.phoneshop.model.order;

import com.es.phoneshop.model.cart.Cart;

import java.util.Objects;

public class Order extends Cart {
    private Long id;
    private String secureId;
    private String name;
    private String deliveryAddress;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecureId() {
        return secureId;
    }

    public void setSecureId(String secureId) {
        this.secureId = secureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
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
        return Objects.equals(id, order.id) &&
                Objects.equals(secureId, order.secureId) &&
                Objects.equals(name, order.name) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(phone, order.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, secureId, name, deliveryAddress, phone);
    }
}
