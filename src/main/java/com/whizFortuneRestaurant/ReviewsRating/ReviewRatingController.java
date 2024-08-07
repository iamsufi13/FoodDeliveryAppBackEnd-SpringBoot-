package com.whizFortuneRestaurant.ReviewsRating;

import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductService;
import com.whizFortuneRestaurant.Users.User;
import com.whizFortuneRestaurant.Users.UserService;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reviewrating")
public class ReviewRatingController {

    @Autowired
    ReviewRatingService reviewRatingService;
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<ReviewRating>>> getAllReviewRating(){
        List<ReviewRating> list = reviewRatingService.getAllReviewRating();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ApiResponse<ReviewRating>>> getReviewRatingById(@PathVariable long id){
        ReviewRating list = reviewRatingService.getById(id);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));

    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<ReviewRating>>> addReviewRating(@RequestParam long userId,
                                                                           @RequestParam long productId,
                                                                           @RequestParam long rating,
                                                                           @RequestParam String reviewmessage,
                                                                           @RequestParam String reviewhead,
                                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    ReviewRating reviewRating = new ReviewRating();
    User user = userService.getUserById(userId);
    reviewRating.setUsers(user);
    Product product = productService.getProductById(productId);
    reviewRating.setProduct(product);
    reviewRating.setReviewmessage(reviewmessage);
    reviewRating.setReviewhead(reviewhead);
    reviewRating.setDate(date);
    reviewRating.setRating(rating);
    System.out.println("detils to add " + reviewRating);
        reviewRatingService.addReviewRating(reviewRating);
        System.out.println("Added ");
        List<ReviewRating> list = reviewRatingService.getAllReviewRating();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
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
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }

}
