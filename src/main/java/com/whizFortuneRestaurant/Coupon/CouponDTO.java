package com.whizFortuneRestaurant.Coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CouponDTO {
    private String coupon_type;
    private String couponCode;
    private int discount;
    private String description;
    private LocalDate valid_till_date;
    private Long productId; // Use ID instead of the whole Product object

    public CouponDTO(String coupon_type, String couponCode, int discount, String description, LocalDate valid_till_date, Long productId) {
        this.coupon_type = coupon_type;
        this.couponCode = couponCode;
        this.discount = discount;
        this.description = description;
        this.valid_till_date = valid_till_date;
        this.productId = productId;
    }

    // Getter and setter for coupon_type
    public String getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(String coupon_type) {
        this.coupon_type = coupon_type;
    }

    // Getter and setter for couponCode
    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    // Getter and setter for discount
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    // Getter and setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and setter for valid_till_date
    public LocalDate getValid_till_date() {
        return valid_till_date;
    }

    public void setValid_till_date(LocalDate valid_till_date) {
        this.valid_till_date = valid_till_date;
    }

    // Getter and setter for productId
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
