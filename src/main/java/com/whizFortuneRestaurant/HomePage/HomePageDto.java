package com.whizFortuneRestaurant.HomePage;

import com.whizFortuneRestaurant.Banner.Banner;
import com.whizFortuneRestaurant.Catlog1.Catlogs;
import com.whizFortuneRestaurant.ComboProducts.ComboProducts;
import com.whizFortuneRestaurant.Coupon.Coupon;
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
    private List<Coupon> coupon;
    private List<ComboProducts> comboProducts;
    private List<Catlogs> catlogs;
    private List<ProductDto> products;
    private List<ProductDto> productsSpeciality;


    public HomePageDto(List<Banner> banner, List<Coupon> coupon, List<ComboProducts> comboProducts, List<Catlogs> catlogs, List<ProductDto> products, List<ProductDto> productsSpeciality) {
        this.banner = banner;
        this.coupon = coupon;
        this.comboProducts = comboProducts;
        this.catlogs = catlogs;
        this.products = products;
        this.productsSpeciality =productsSpeciality;



    }


}
