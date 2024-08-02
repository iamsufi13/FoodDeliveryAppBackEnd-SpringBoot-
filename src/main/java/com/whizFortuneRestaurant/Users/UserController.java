package com.whizFortuneRestaurant.Users;

import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<List<ApiResponse<User>>> getAllUser(){
        List<User> list = userService.getAllUser();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));

    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ApiResponse<User>>> getUserById(@PathVariable long id){
        User list = userService.getUserById(id);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<User>>> addUser(@RequestBody User user){
        System.out.println("detils to add " + user);
        userService.addUser(user);
        System.out.println("Added ");
        List<User> list = userService.getAllUser();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success","true"));
    }

    @GetMapping("number/{mobile}")
    public ResponseEntity<List<ApiResponse<User>>> checkNumber(@PathVariable long mobile){
        Random random = new Random();
        int random_int1 = random.nextInt(10000);
        System.out.println(random_int1);
        List list = new ArrayList<>();
        list.add(random_int1);
        System.out.println("number entered " + mobile);
        userService.checkmobile(mobile);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","true"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateReviewRating(@PathVariable long id, @RequestBody User user) {
        System.out.println("updating  "+ user);
        User updateUser = userService.updateUser(id,user);
        System.out.println("updated");

        return ResponseEntity.ok(updateUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<User>>> deleteReviewRatingById(@PathVariable long id){
        System.out.println("detils to add " + id);
        userService.deleteUserById(id);
        System.out.println("Deleted ");
        List<User> list = userService.getAllUser();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success","true"));
    }
}
