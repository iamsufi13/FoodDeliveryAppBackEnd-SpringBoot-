package com.whizFortuneRestaurant.Orders;

import com.whizFortuneRestaurant.OrderProduct.OrderProduct;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
    private long id;
    private long txnid;
    private List<Long> productIds;  // Updated to List<Long>
    private List<OrderProduct> orderProduct;
    private List<Long> availableSizeIds;  // Updated to List<Long>
    private long orderStatusId;
    private long userId;
    private int price;
    private int mrp;
    private int qty;
    private int status;
    private int discountPrice;
    private LocalDateTime dt1;


    public <R> OrderDto(long id, long txnid, R r, R r1, long l, long l1, int price, int mrp, int qty, int status, int discountprice, LocalDateTime dt1) {
    }
}
