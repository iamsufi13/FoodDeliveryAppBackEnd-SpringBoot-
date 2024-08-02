package com.whizFortuneRestaurant.DealOfTheDay;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<DealOfTheDay> addDealsOfTheDay(@RequestBody DealOfTheDay dealOfTheDay){
         DealOfTheDay dealOfTheDay1 =dealOfTheDayService.addDealsOfTheDay(dealOfTheDay);
//        return ResponseEntity.ok((DealOfTheDay) list);
        return ResponseEntity.status(HttpStatus.CREATED).body(dealOfTheDay1);
    }
}
