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
import com.whizFortuneRestaurant.Product.ProductByTagDto;
import com.whizFortuneRestaurant.Product.ProductDto;
import com.whizFortuneRestaurant.Product.ProductListingDto;

import java.util.List;

public class HomePageMapper {
    public static HomePageDto toDto(List<Banner> banners, List<CouponDTO> coupons, List<ProductListingDto> products, List<DealOfTheDayDto> dealOfTheDayDtos, List<ComboProductsDto> comboProducts, List<Catlogs> catlogs, List<ProductByTagDto> productsSpeciality, List<OrderDto> recentOrders) {
        if (banners == null && coupons == null && products == null && comboProducts == null && catlogs == null && productsSpeciality == null) {
            return null;
        }
        return new HomePageDto(
                banners,
                catlogs,
                products,
                dealOfTheDayDtos,
                coupons,
                comboProducts,
                productsSpeciality,
                recentOrders
        );
    }
}
