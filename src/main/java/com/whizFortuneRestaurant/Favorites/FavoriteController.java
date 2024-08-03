package com.whizFortuneRestaurant.Favorites;

import com.whizFortuneRestaurant.Cart.Cart;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<FavoriteDto>>> getAllFavorite(){
        List<FavoriteDto> list = favoriteService.getAllFavorite();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));

    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Favorite>>> getSingleFavorite(@PathVariable long id){
        Favorite list = favoriteService.getFavoriteById(id);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }
    @PostMapping(consumes = "application/json")
    public ResponseEntity<List<ApiResponse<FavoriteDto>>> addFavorite(@RequestBody Favorite favorite){
        System.out.println("Details to add: " + favorite);
        favoriteService.addToFavorite(favorite);
        System.out.println("Added");
        List<FavoriteDto> list = favoriteService.getAllFavorite();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFav(@RequestBody Favorite favorite) {
        try {
            favoriteService.favoriteRepository.save(favorite);
            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<Favorite> updatefavorite(@PathVariable long id, @RequestBody Favorite favorite) {
        System.out.println("updating  "+ favorite);
        Favorite updateFavorite = favoriteService.updateFavorite(id, favorite);
        System.out.println("updated");
        return ResponseEntity.ok(updateFavorite);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Favorite>>> deleteFavoriteById(@PathVariable long id){
        System.out.println("detils to add " + id);
        favoriteService.deleteFavoriteById(id);
        System.out.println("Deleted ");
        List<FavoriteDto> list = favoriteService.getAllFavorite();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }



}
