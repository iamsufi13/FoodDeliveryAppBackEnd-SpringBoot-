package com.whizFortuneRestaurant.WishList;

import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;

    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestBody WishListRequest wishListRequest) {
        WishList wishList =wishListService.add(wishListRequest.getUserId(), wishListRequest.getProductId());
        return ResponseEntity.ok("Favorite added successfully " + wishList);
    }
}
