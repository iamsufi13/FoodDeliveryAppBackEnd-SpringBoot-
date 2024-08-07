package com.whizFortuneRestaurant.DealOfTheDay;

import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductService;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dealoftheday")
public class DealOfTheDayController {
    @Autowired
    DealOfTheDayService dealOfTheDayService;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<List<ApiResponse<DealOfTheDay>>> addDealsOfTheDay(
            @RequestParam int offer_price,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_date,
            @RequestParam int status,
            @RequestParam long productId
            ){
         DealOfTheDay dealOfTheDay1 = new DealOfTheDay();
         dealOfTheDay1.setOffer_price(offer_price);
         dealOfTheDay1.setEnd_date(end_date);
         dealOfTheDay1.setStatus(status);
        System.out.println(productId);
        Product product = productService.getProductById(productId);

        dealOfTheDay1.setProduct(product);
        dealOfTheDayService.addDealsOfTheDay(dealOfTheDay1);
        List<DealOfTheDay> list = dealOfTheDayService.dealOfTheDayRepository.findAll();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }

    @GetMapping
    public ResponseEntity<List<ApiResponse<DealOfTheDayDto>>> getAllDealOfTheDay(){
        List<DealOfTheDayDto> list = dealOfTheDayService.getAllDealsOfTheDay();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
}
