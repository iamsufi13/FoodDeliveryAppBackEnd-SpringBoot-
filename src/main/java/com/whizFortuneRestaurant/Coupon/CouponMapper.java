package com.whizFortuneRestaurant.Coupon;

public class CouponMapper {
    public static CouponDTO toCouponDto(Coupon coupon){
        if (coupon == null){
            return null;
        }

        return new CouponDTO(
                coupon.getCouponCode(),
                coupon.getCoupon_type(),
                coupon.getDiscount(),
                coupon.getDescription(),
                coupon.getValid_till_date(),
                coupon.getProduct().getId()
        );
    }
}
