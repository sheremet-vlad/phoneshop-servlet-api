package com.es.phoneshop.dao.reviewDao;

import com.es.phoneshop.model.review.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ArrayListReviewDao<T extends Review> extends ReviewDao<T> {

    private static volatile ReviewDao instance;
    private final static Object lock = new Object();

    private ArrayListReviewDao() {

    }

    public static ReviewDao getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ArrayListReviewDao();
                }
            }
        }

        return instance;
    }

    @Override
    public List<T> getProductReview(Long productId) {
        return entities.stream().
                filter(review -> review.getProductId().equals(productId))
                .collect(Collectors.toList());
    }
}
