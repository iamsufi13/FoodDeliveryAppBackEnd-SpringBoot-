package com.whizFortuneRestaurant.HomePage;

import com.whizFortuneRestaurant.Banner.Banner;
import com.whizFortuneRestaurant.Catlog1.Catlogs;
import com.whizFortuneRestaurant.ComboProducts.ComboProducts;
import com.whizFortuneRestaurant.Coupon.Coupon;
import com.whizFortuneRestaurant.Coupon.CouponDTO;
import com.whizFortuneRestaurant.DealOfTheDay.DealOfTheDay;
import com.whizFortuneRestaurant.DealOfTheDay.DealOfTheDayDto;
import com.whizFortuneRestaurant.Orders.OrderDto;
import com.whizFortuneRestaurant.Orders.Orders;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class HomePageDto {
    private List<Banner> banner;
    private List<Catlogs> catlogs;
    private List<ProductDto> products;
    private List<DealOfTheDayDto> dealOfTheDayDto;
    private List<CouponDTO> coupon;
    private List<ComboProducts> comboProducts;
    private List<ProductDto> productsSpeciality;
    private List<OrderDto> recentOrders;


    public HomePageDto(List<Banner> banner, List<Catlogs> catlogs, List<ProductDto> products, List<DealOfTheDayDto> dealOfTheDayDtos, List<CouponDTO> coupon, List<ComboProducts> comboProducts, List<ProductDto> productsSpeciality,List<OrderDto> recentOrders) {
        this.banner = banner;
        this.catlogs = catlogs;
        this.products = products;
        this.dealOfTheDayDto=dealOfTheDayDtos;
        this.coupon = coupon;
        this.comboProducts = comboProducts;
        this.productsSpeciality =productsSpeciality;
        this.recentOrders=recentOrders;



    }


}
