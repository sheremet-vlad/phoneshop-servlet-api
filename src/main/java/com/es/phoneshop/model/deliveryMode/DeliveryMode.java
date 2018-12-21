package com.es.phoneshop.model.deliveryMode;

import com.es.phoneshop.model.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class DeliveryMode extends Entity {
    private BigDecimal cost;
    private String name;

    public DeliveryMode() {
    }

    public DeliveryMode(BigDecimal cost, String name) {
        super();
        this.cost = cost;
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeliveryMode that = (DeliveryMode) o;
        return Objects.equals(cost, that.cost) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cost, name);
    }

    @Override
    public String toString() {
        return "DeliveryMode{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}
