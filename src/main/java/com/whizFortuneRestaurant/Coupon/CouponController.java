package com.whizFortuneRestaurant.Coupon;

import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    @Autowired
    CouponService couponService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<Coupon>>> getAllCoupon(){
        List<Coupon> list=couponService.getAllCoupon();
        System.out.println(list);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
    }
    @GetMapping("/general")
    public List<Coupon> getAllCouponWithourDependency(){
        return couponService.getAllCoupon();
    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<Coupon>>> saveCoupon(@RequestBody Coupon coupon){
        couponService.saveCoupon(coupon);
        List<Coupon> list=couponService.getAllCoupon();
        System.out.println("Coupon added Succesfully" + coupon);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
    }
    @GetMapping("/{id}")
    public List<HashMap> getCouponById(@PathVariable long id){
        HashMap hashMap = new HashMap();
        Coupon coupon= couponService.getCouponById(id);
        hashMap.put("data",coupon);
        hashMap.put("message","SUCCESS");
        hashMap.put("status","true");
        return Collections.singletonList(hashMap);
    }
    @PutMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Coupon>>> updateCoupon(@PathVariable long id,@RequestBody Coupon coupon){
        couponService.updateCoupon(id,coupon);
        List<Coupon> list=couponService.getAllCoupon();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Coupon>>> deleteCouponById(@PathVariable long id){
        couponService.deleteCouponById(id);
        List<Coupon> list = couponService.getAllCoupon();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", "ok"));
    }


}
