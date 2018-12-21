package com.es.phoneshop.service.reviewService;

import com.es.phoneshop.model.product.Product;

public interface ReviewService {
    void placeReview(Long productId, String name, String comments, int mark);
}
