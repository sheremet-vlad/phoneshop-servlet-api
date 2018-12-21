package com.es.phoneshop.model.review;

import com.es.phoneshop.model.Entity;

import java.util.Objects;

public class Review extends Entity {
    private String name;
    private Long productId;
    private String comments;
    private int mark;

    public Review() {
    }

    public Review(Long id, String name, Long productId, String comments, int mark) {
        super(id);
        this.name = name;
        this.productId = productId;
        this.comments = comments;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCommnts() {
        return comments;
    }

    public void setCommnts(String commnts) {
        this.comments = commnts;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return mark == review.mark &&
                Objects.equals(name, review.name) &&
                Objects.equals(productId, review.productId) &&
                Objects.equals(comments, review.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, productId, comments, mark);
    }

    @Override
    public String toString() {
        return "Review{" +
                "name='" + name + '\'' +
                ", productId=" + productId +
                ", commnts='" + comments + '\'' +
                ", mark=" + mark +
                '}';
    }
}
