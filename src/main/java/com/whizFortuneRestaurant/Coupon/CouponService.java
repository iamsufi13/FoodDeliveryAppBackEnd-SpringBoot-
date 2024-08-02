package com.whizFortuneRestaurant.Coupon;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class CouponService {

    @Autowired
    CouponRepository repository;

    public List<Coupon> getAllCoupon() {
        return repository.findAll();
    }

    public void saveCoupon(Coupon coupon) {
        repository.save(coupon);
    }

    public Coupon getCouponById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void updateCoupon(long id, Coupon coupon) {
        Coupon coupon1 = coupon;
        if (coupon1!=null){
            coupon1.setCouponCode(coupon.getCouponCode());
            coupon1.setCoupon_type(coupon.getCoupon_type());
            coupon1.setDescription(coupon.getDescription());
            coupon1.setProduct(coupon.getProduct());
            coupon1.setValid_till_date(coupon.getValid_till_date());
            coupon1.setDiscount(coupon.getDiscount());
            repository.save(coupon1);
        }
    }

    public void deleteCouponById(long id) {
        repository.deleteById(id);
    }
}
