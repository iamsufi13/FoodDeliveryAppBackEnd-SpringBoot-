package com.whizFortuneRestaurant.WishList;

import lombok.Data;

@Data
public class WishListRequest {
    private long userId;

    private long productId;
}
