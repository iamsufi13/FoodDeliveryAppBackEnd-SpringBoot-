package com.whizFortuneRestaurant.DealOfTheDay;

import com.whizFortuneRestaurant.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DealOfTheDayDto {
    private long id;
    private int offer_price;
    private LocalDate end_date;
    private int status;
    private long productId;


}
