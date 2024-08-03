//package com.whizFortuneRestaurant.CustomizationProduct;
//
//import com.whizFortuneRestaurant.Utils.ApiResponse;
//import com.whizFortuneRestaurant.Utils.ResponseUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/customizationproduct")
//public class CustomizationProductControlller {
//    @Autowired
//    CustomizationProductService customizationProductService;
//    @GetMapping
//    public ResponseEntity<List<ApiResponse<CustomizationProduct>>> getAllCustomizationProducts(){
//        List<CustomizationProduct> list= customizationProductService.getAll();
//        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
//    }
//
//    @PostMapping
//    public ResponseEntity<List<ApiResponse<CustomizationProduct>>> saveCustomizationProduct(@RequestBody CustomizationProduct customizationProduct){
//        customizationProductService.saveCustomizationProduct(customizationProduct);
//        List<CustomizationProduct> list= customizationProductService.getAll();
//        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<List<ApiResponse<CustomizationProduct>>> updateCustomizationProduct(@PathVariable long id,@RequestBody CustomizationProduct customizationProduct){
//        customizationProductService.updateCustomizationProduct(id,customizationProduct);
//        List<CustomizationProduct> list= customizationProductService.getAll();
//        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<List<ApiResponse<CustomizationProduct>>> deleteCustomizationProduct(@PathVariable long id){
//        customizationProductService.deleteCustomiationProductById(id);
//        List<CustomizationProduct> list= customizationProductService.getAll();
//        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
//    }
//
//
//
//}
package com.whizFortuneRestaurant.CustomizationProduct;

import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customizationproduct")
public class CustomizationProductControlller {
    @Autowired
    CustomizationProductService customizationProductService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<CustomizationProduct>>> getAllCustomizationProducts() {
        List<CustomizationProduct> list = customizationProductService.getAll();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }
    @GetMapping("/general")
    public List<CustomizationProduct> getAllCustomizationProductWithoutDependency(){
        return customizationProductService.getAll();
    }

    @PostMapping
    public ResponseEntity<List<ApiResponse<CustomizationProduct>>> saveCustomizationProduct(@RequestBody CustomizationProduct customizationProduct) {
        customizationProductService.saveCustomizationProduct(customizationProduct);
        List<CustomizationProduct> list = customizationProductService.getAll();
        System.out.println("CSuomization Product added successfully "+customizationProduct);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<ApiResponse<CustomizationProduct>>> updateCustomizationProduct(@PathVariable long id, @RequestBody CustomizationProduct customizationProduct) {
        customizationProductService.updateCustomizationProduct(id, customizationProduct);
        List<CustomizationProduct> list = customizationProductService.getAll();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<CustomizationProduct>>> deleteCustomizationProduct(@PathVariable long id) {
        customizationProductService.deleteCustomiationProductById(id);
        List<CustomizationProduct> list = customizationProductService.getAll();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }
}
