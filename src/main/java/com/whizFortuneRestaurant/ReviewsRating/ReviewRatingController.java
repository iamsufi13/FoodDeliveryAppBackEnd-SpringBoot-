package com.whizFortuneRestaurant.ReviewsRating;

import com.whizFortuneRestaurant.Favorites.Favorite;
import com.whizFortuneRestaurant.OrderStatus.OrderStatus;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviewrating")
public class ReviewRatingController {

    @Autowired
    ReviewRatingService reviewRatingService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<ReviewRating>>> getAllReviewRating(){
        List<ReviewRating> list = reviewRatingService.getAllReviewRating();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ApiResponse<ReviewRating>>> getReviewRatingById(@PathVariable long id){
        ReviewRating list = reviewRatingService.getById(id);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));

    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<ReviewRating>>> addReviewRating(@RequestBody ReviewRating reviewRating){
        System.out.println("detils to add " + reviewRating);
        reviewRatingService.addReviewRating(reviewRating);
        System.out.println("Added ");
        List<ReviewRating> list = reviewRatingService.getAllReviewRating();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success","true"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewRating> updateReviewRating(@PathVariable long id, @RequestBody ReviewRating reviewRating) {
        System.out.println("updating  "+ reviewRating.toString());
        ReviewRating updateReviewRating = reviewRatingService.updateReviewRating(id,reviewRating);
        System.out.println("updated");
        return ResponseEntity.ok(updateReviewRating);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<ReviewRating>>> deleteReviewRatingById(@PathVariable long id){
        System.out.println("detils to add " + id);
        reviewRatingService.deleteReviewRatingById(id);
        System.out.println("Deleted ");
        List<ReviewRating> list = reviewRatingService.getAllReviewRating();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success","true"));
    }

}
