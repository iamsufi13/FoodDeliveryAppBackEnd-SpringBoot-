package com.whizFortuneRestaurant.HomePage;

import com.whizFortuneRestaurant.Banner.Banner;
import com.whizFortuneRestaurant.Catlog1.Catlogs;
import com.whizFortuneRestaurant.ComboProducts.ComboProducts;
import com.whizFortuneRestaurant.ComboProducts.ComboProductsDto;
import com.whizFortuneRestaurant.Coupon.Coupon;
import com.whizFortuneRestaurant.Coupon.CouponDTO;
import com.whizFortuneRestaurant.DealOfTheDay.DealOfTheDay;
import com.whizFortuneRestaurant.DealOfTheDay.DealOfTheDayDto;
import com.whizFortuneRestaurant.Orders.OrderDto;
import com.whizFortuneRestaurant.Orders.Orders;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductByTagDto;
import com.whizFortuneRestaurant.Product.ProductDto;
import com.whizFortuneRestaurant.Product.ProductListingDto;
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
    private List<ProductListingDto> products;
    private List<DealOfTheDayDto> dealOfTheDayDto;
    private List<CouponDTO> coupon;
    private List<ComboProductsDto> comboProducts;
    private List<ProductByTagDto> productsSpeciality;
    private List<OrderDto> recentOrders;


    public HomePageDto(List<Banner> banner, List<Catlogs> catlogs, List<ProductListingDto> products, List<DealOfTheDayDto> dealOfTheDayDtos, List<CouponDTO> coupon, List<ComboProductsDto> comboProducts, List<ProductByTagDto> productsSpeciality, List<OrderDto> recentOrders) {
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
