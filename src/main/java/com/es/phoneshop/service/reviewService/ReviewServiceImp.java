package com.es.phoneshop.service.reviewService;

import com.es.phoneshop.dao.reviewDao.ArrayListReviewDao;
import com.es.phoneshop.dao.reviewDao.ReviewDao;
import com.es.phoneshop.model.review.Review;

public class ReviewServiceImp implements ReviewService{
    private static volatile ReviewService instance;
    private final static Object lock = new Object();

    private ReviewServiceImp() {

    }

    public static ReviewService getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ReviewServiceImp();
                }
            }
        }

        return instance;
    }

    @Override
    public void placeReview(Long productId, String name, String comments, int mark) {
        ReviewDao<Review> reviewDao = ArrayListReviewDao.getInstance();

        Review review = new Review();
        review.setName(name);
        review.setCommnts(comments);
        review.setProductId(productId);
        review.setMark(mark);

        reviewDao.save(review);
    }
}
