package com.whizFortuneRestaurant.AvailableISizes;

import com.whizFortuneRestaurant.Product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvailableSizesDto {
    private long id;
    private String sizeName;
    private int price;
    private int status;
    private LocalDateTime dt1;
    private ProductDto product; // Add this line to include the product reference
}
