package com.whizFortuneRestaurant.Orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private long id;
    private long txnid;
    private List<Long> productIds;
    private List<Long> availableSizeIds;
    private long orderStatusId;
    private long userId;
    private int price;
    private int mrp;
    private int qty;
    private int status;
    private int discountPrice;
    private LocalDateTime dt1;
}
