package com.whizFortuneRestaurant.NutritionData;

import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductService;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutritiondata")
public class NutritionDataController {
    @Autowired
    NutritionDataService nutritionDataService;

    @Autowired
    ProductService productService;
    @GetMapping
    public ResponseEntity<List<ApiResponse<NutritionData>>> getAllNutritionData(){
        List<NutritionData> list = nutritionDataService.getAllNutritionData();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @GetMapping("/general")
    public List<NutritionDataDto> getAllNutritionDataWithoutDependency(){
        return nutritionDataService.getAllNutritionDataDto();
    }

    @PostMapping
    public ResponseEntity<List<ApiResponse<NutritionData>>> saveNutritionData(
            @RequestParam int status,
            @RequestParam int fats,
            @RequestParam int proteins,
            @RequestParam int calories,
            @RequestParam int carbs,
            @RequestParam long productId
    ){
        NutritionData nutritionData1 = new NutritionData();
        nutritionData1.setStatus(status);
        nutritionData1.setFats(fats);
        nutritionData1.setProteins(proteins);
        nutritionData1.setCalories(calories);
        nutritionData1.setCarbs(carbs);
        Product product = productService.getProductById(productId);
        nutritionData1.setProduct(product);
        nutritionDataService.saveNutritionData(nutritionData1);
        List<NutritionData> list = nutritionDataService.getAllNutritionData();
        System.out.println("added succ"+nutritionData1);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @PutMapping("/{id}")
    public ResponseEntity<List<ApiResponse<NutritionData>>> updateNutritionData(@PathVariable long id,@RequestBody NutritionData nutritionData){
        System.out.println("into controller Nutritiondata "+nutritionData);
        nutritionDataService.updateNutritionData(id,nutritionData);
        System.out.println("Saved");
        List<NutritionData> list = nutritionDataService.getAllNutritionData();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<NutritionData>>> deleteNutritionData(@PathVariable long id){
        nutritionDataService.deleteNutritionData(id);
        List<NutritionData> list = nutritionDataService.getAllNutritionData();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }

}
