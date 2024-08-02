package com.whizFortuneRestaurant.ComboProducts;

import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comboproduct")
public class ComboProductController {
    @Autowired
    ComboProductService comboProductService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<ComboProducts>>> getAllComboProducts(){
        List<ComboProducts> list = comboProductService.getAllComboProducts();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<ComboProducts>>> addComboProducts(@RequestBody ComboProducts comboProducts){
        comboProductService.comboProductsReposiory.save(comboProducts);
        List<ComboProducts> list = comboProductService.getAllComboProducts();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));
    }
}
