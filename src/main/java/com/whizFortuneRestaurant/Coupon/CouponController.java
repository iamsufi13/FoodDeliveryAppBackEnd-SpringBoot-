package com.whizFortuneRestaurant.Coupon;

import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductController;
import com.whizFortuneRestaurant.Product.ProductService;
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

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<Coupon>>> getAllCoupon(){
        List<Coupon> list=couponService.getAllCoupon();
        System.out.println(list);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @GetMapping("/general")
    public List<Coupon> getAllCouponWithourDependency(){
        return couponService.getAllCoupon();
    }
//    @PostMapping
//    public ResponseEntity<List<ApiResponse<Coupon>>> saveCoupon(@RequestBody Coupon coupon){
//        System.out.println(coupon.getProduct());
////        Coupon coupon1 = new Coupon();
////        coupon1.setCouponCode(coupon.getCouponCode());
////        coupon1.setCoupon_type(coupon.getCoupon_type());
////        //coupon1.setProduct(coupon.);
////        couponService.saveCoupon(coupon);
////        List<Coupon> list=couponService.getAllCoupon();
////        System.out.println("Coupon added Succesfully" + coupon);
//        Object list = null;
//        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
//    }

    @GetMapping("/coupondto")
    public ResponseEntity<List<ApiResponse<CouponDTO>>> getAllCouponDto(){
        List<CouponDTO> list=couponService.getAllCouponDto();
        System.out.println(list);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }


    @PostMapping
    public ResponseEntity<List<ApiResponse<Coupon>>> saveCoupon(@RequestBody CouponDTO couponDTO) {
        // Convert DTO to entity
        Coupon coupon = new Coupon();
        coupon.setCoupon_type(couponDTO.getCoupon_type());
        coupon.setCouponCode(couponDTO.getCouponCode());
        coupon.setDiscount(couponDTO.getDiscount());
        coupon.setDescription(couponDTO.getDescription());
        coupon.setValid_till_date(couponDTO.getValid_till_date());

        // Handle the product association

        Product product = productService.getProductById(couponDTO.getProductId()); // Assuming you have a method to fetch product by ID
        coupon.setProduct(product);

        System.out.println(coupon.toString());
        // Save coupon
        couponService.saveCoupon(coupon);

        // Fetch updated list and return response
        List<Coupon> list = couponService.getAllCoupon();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
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
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Coupon>>> deleteCouponById(@PathVariable long id){
        couponService.deleteCouponById(id);
        List<Coupon> list = couponService.getAllCoupon();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }


}
