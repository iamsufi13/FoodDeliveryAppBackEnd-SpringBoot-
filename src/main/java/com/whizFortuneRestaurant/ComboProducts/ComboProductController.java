package com.whizFortuneRestaurant.ComboProducts;

import com.whizFortuneRestaurant.ComboProductDetails.ComboProductDetails;
import com.whizFortuneRestaurant.Product.FoodType;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductService;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/comboproduct")
public class ComboProductController {

    @Autowired
    private ComboProductService comboProductService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<ComboProductsDto>>> getAllComboProducts() {
        List<ComboProductsDto> list = comboProductService.getAllComboProducts();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }
    @GetMapping("/combo-products")
    public ResponseEntity<ComboProductsDto> getComboProduct(@RequestParam long id) {
        ComboProducts comboProducts = comboProductService.findById(id);
        ComboProductsDto dto = ComboProductsMapper.toComboProductsDto(comboProducts);
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<List<ApiResponse<ComboProducts>>> addComboProducts(
            @RequestParam("description") String description,
            @RequestParam("price") int price,
            @RequestParam("mrp") int mrp,
            @RequestParam("foodType") FoodType foodType,
            @RequestParam("oniongarlic") int oniongarlic,
            @RequestParam("freedelivery") int freedelivery,
            @RequestParam("spicy") int spicy,
            @RequestParam("productId1") long productId1,
            @RequestParam("productId1Quantity") int productId1Quantity,
            @RequestParam("productId2") long productId2,
            @RequestParam("productId2Quantity") int productId2Quantity
    ) {
        ComboProducts comboProducts = new ComboProducts();
        comboProducts.setDescription(description);
        comboProducts.setMrp(mrp);
        comboProducts.setPrice(price);
        comboProducts.setSpicy(spicy);
        comboProducts.setOniongarlic(oniongarlic);
        comboProducts.setFreedeliverystatus(freedelivery);
        comboProducts.setFoodType(foodType);

        // Create ComboProductDetails for each product
        List<ComboProductDetails> productDetailsList = new ArrayList<>();

        // Product 1
        Product product1 = productService.getProductById(productId1);
        if (product1 != null) {
            ComboProductDetails details1 = new ComboProductDetails();
            details1.setProduct(product1);
            details1.setComboProducts(comboProducts);
            details1.setQuantity(productId1Quantity);
            productDetailsList.add(details1);
        } else {
            return ResponseEntity.badRequest().body(ResponseUtils.createResponse(null, "Product 1 not found", false));
        }

        // Product 2
        Product product2 = productService.getProductById(productId2);
        if (product2 != null) {
            ComboProductDetails details2 = new ComboProductDetails();
            details2.setProduct(product2);
            details2.setComboProducts(comboProducts);
            details2.setQuantity(productId2Quantity);
            productDetailsList.add(details2);
        } else {
            return ResponseEntity.badRequest().body(ResponseUtils.createResponse(null, "Product 2 not found", false));
        }

        comboProducts.setProductDetails(productDetailsList);
        comboProductService.comboProductsReposiory.save(comboProducts);

        List<ComboProductsDto> comboProductsList = comboProductService.getAllComboProducts();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(comboProductsList, "SUCCESS", true));
    }
}
