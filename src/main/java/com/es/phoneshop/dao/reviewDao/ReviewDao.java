package com.es.phoneshop.dao.reviewDao;

import com.es.phoneshop.dao.DaoImpl;
import com.es.phoneshop.model.review.Review;

import java.util.List;

public abstract class ReviewDao<T extends Review> extends DaoImpl<T> {
    public abstract List<T> getProductReview(Long productId);
}
