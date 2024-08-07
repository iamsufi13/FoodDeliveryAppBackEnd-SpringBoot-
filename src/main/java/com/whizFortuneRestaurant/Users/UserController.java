package com.whizFortuneRestaurant.Users;

import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<List<ApiResponse<UserDto>>> getAllUser(){
        List<UserDto> list = userService.getAllUser();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));

    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@RequestParam long id){
        User list = userService.getUserById(id);
//        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","true"));
        return ResponseEntity.ok(list);
    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<User>>> addUser(@RequestParam String username,
                                                           @RequestParam long mobile,
                                                           @RequestParam String emailId,
                                                           @RequestParam String gender,
                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
                                                           @RequestParam String devicetype,
                                                           @RequestParam String fcmtoken,
                                                           @RequestParam int status,
                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate anniversarydate){
        User user1 = new User();
        user1.setUsername(username);
        user1.setMobile(mobile);
        user1.setEmailId(emailId);
        user1.setGender(gender);
        user1.setDob(dob);
        user1.setDevicetype(devicetype);
        user1.setFcmtoken(fcmtoken);
        user1.setStatus(status);
        user1.setAnniversarydate(anniversarydate);
        System.out.println("detils to add " + user1);
        userService.addUser(user1);
        System.out.println("Added ");
        List<UserDto> list = userService.getAllUser();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }

    @GetMapping("number")
    public ResponseEntity<List<ApiResponse<User>>> checkNumber(@RequestParam long mobile){
        Random random = new Random();
        int random_int1 = random.nextInt(10000);
        System.out.println(random_int1);
        User user =userService.checkmobile(mobile);
        List list = new ArrayList();
        HashMap hashMap = new HashMap<>();
        if (user!=null){
        hashMap.put("userId", user.getUserid());
        hashMap.put("userName", user.getUsername());
        hashMap.put("otp", random_int1);
        hashMap.put("mobile", user.getMobile());
        hashMap.put("email", user.getEmailId());
        hashMap.put("gender", user.getGender());
        hashMap.put("message","Reistered User");
        hashMap.put("isUserRegistered",true);
            list.add(hashMap);
            return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));}
        else {
            hashMap.put("otp",random_int1);
            hashMap.put("message","User Not Registerd");
            hashMap.put("isUserRegistered",false);
            list.add(hashMap);
            return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",false));
        }

    }

    @PutMapping()
    public ResponseEntity<List<ApiResponse<User>>> updateUser(@RequestParam long id,
                                                   @RequestParam String username,
                                                   @RequestParam long mobile,
                                                   @RequestParam String emailId,
                                                   @RequestParam String gender,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
                                                   @RequestParam String devicetype,
                                                   @RequestParam String fcmtoken,
                                                   @RequestParam int status,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate anniversarydate) {
        User user1 = userService.getUserById(id);
        user1.setUsername(username);
        user1.setMobile(mobile);
        user1.setEmailId(emailId);
        user1.setGender(gender);
        user1.setDob(dob);
        user1.setDevicetype(devicetype);
        user1.setFcmtoken(fcmtoken);
        user1.setStatus(status);
        user1.setAnniversarydate(anniversarydate);
        System.out.println("updating  "+ user1);
        userService.updateUser(id,user1);
        System.out.println("updated");
        List<UserDto> list = userService.getAllUser();

        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @DeleteMapping()
    public ResponseEntity<List<ApiResponse<User>>> deleteReviewRatingById(@RequestParam long id){
        System.out.println("detils to add " + id);
        userService.deleteUserById(id);
        System.out.println("Deleted ");
        List<UserDto> list = userService.getAllUser();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }
}
