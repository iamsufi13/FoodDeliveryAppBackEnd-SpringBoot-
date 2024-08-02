package com.whizFortuneRestaurant.HomePage;

import com.whizFortuneRestaurant.Banner.Banner;
import com.whizFortuneRestaurant.Catlog1.Catlogs;
import com.whizFortuneRestaurant.ComboProducts.ComboProducts;
import com.whizFortuneRestaurant.Coupon.Coupon;
import com.whizFortuneRestaurant.Product.ProductDto;

import java.util.List;

public class HomePageMapper {
    public static HomePageDto toDto(List<Banner> banners, List<Coupon> coupons, List<ProductDto> products, List<ComboProducts> comboProducts, List<Catlogs> catlogs, List<ProductDto> productsSpeciality) {
        if (banners == null && coupons == null && products == null && comboProducts == null && catlogs == null && productsSpeciality == null) {
            return null;
        }
        return new HomePageDto(
                banners,
                coupons,
                comboProducts,
                catlogs,
                products,
                productsSpeciality
        );
    }
}
