package com.whizFortuneRestaurant.Coupon;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
