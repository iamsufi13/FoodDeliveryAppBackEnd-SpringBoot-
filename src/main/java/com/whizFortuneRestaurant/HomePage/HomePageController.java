package com.whizFortuneRestaurant.HomePage;

import com.whizFortuneRestaurant.Banner.Banner;
import com.whizFortuneRestaurant.Banner.BannerService;
import com.whizFortuneRestaurant.Catlog1.CatlogService;
import com.whizFortuneRestaurant.Catlog1.Catlogs;
import com.whizFortuneRestaurant.ComboProducts.ComboProductService;
import com.whizFortuneRestaurant.ComboProducts.ComboProducts;
import com.whizFortuneRestaurant.Coupon.Coupon;
import com.whizFortuneRestaurant.Coupon.CouponService;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductDto;
import com.whizFortuneRestaurant.Product.ProductService;
import com.whizFortuneRestaurant.Utils.HomePageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomePageController {

    @Autowired
    private CatlogService catlogService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private ComboProductService comboProductsService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BannerService bannerService;

    @GetMapping
    public ResponseEntity<HomePageResponse<HomePageDto>> getAllHomeAssets() {
        List<Banner> bannerList = bannerService.getAllBanners();
        List<Catlogs> catlogsList = catlogService.getAllCatlog();
        List<Coupon> couponList = couponService.getAllCoupon();
        List<ComboProducts> comboProductsList = comboProductsService.getAllComboProducts();
        List<ProductDto> productList = productService.getAllProductsExcludeOtherEntities();
        List<ProductDto> productsTags = productService.getProductsBySpeciality();

        // Create HomePageDto
        HomePageDto homePageDto = new HomePageDto(
                bannerList,
                couponList,
                comboProductsList,
                catlogsList,
                productList,
                productsTags
        );

        // Wrap the HomePageDto in a List for HomePageResponse
        List<HomePageDto> homePageDtoList = new ArrayList<>();
        homePageDtoList.add(homePageDto);

        HomePageResponse<HomePageDto> response = new HomePageResponse<>(
                "SUCCESS",
                "ok",
                homePageDtoList

        );
        System.out.println(response.toString());

        return ResponseEntity.ok(response);
    }
}
