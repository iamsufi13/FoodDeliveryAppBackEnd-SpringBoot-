package com.whizFortuneRestaurant.ReviewsRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewRatingService {
    @Autowired
    ReviewRatingRepository reviewRatingRepository;

    public List<ReviewRating> getAllReviewRating() {
        return reviewRatingRepository.findAll();
    }

    public ReviewRating getById(long id) {
        return reviewRatingRepository.findById(id).orElse(null);
    }
    public void addReviewRating(ReviewRating reviewRating){
        reviewRatingRepository.save(reviewRating);
    }
    public ReviewRating updateReviewRating(long id, ReviewRating reviewRating){
        ReviewRating reviewRating1 = getById(id);
        if (reviewRating1 != null){
        System.out.println(reviewRating.toString() + " reviewrating");
        System.out.println(reviewRating1 + " reviewrating1");
        reviewRating1.setProduct(reviewRating.getProduct());
        reviewRating1.setReviewhead(reviewRating.getReviewhead());
        reviewRating1.setReviewmessage(reviewRating.getReviewmessage());
        reviewRating1.setUsers(reviewRating.getUsers());
        reviewRating1.setStatus(reviewRating.getStatus());
        reviewRating1.setDate(reviewRating.getDate());
        reviewRating1.setRating(reviewRating.getRating());
        return reviewRatingRepository.save(reviewRating1);}
        else {
            System.out.println("reviewrating 1 is null");
            return null;
        }
    }
    public void deleteReviewRatingById(long id){
        reviewRatingRepository.deleteById(id);
    }
}
