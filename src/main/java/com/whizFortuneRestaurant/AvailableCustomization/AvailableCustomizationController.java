package com.whizFortuneRestaurant.AvailableCustomization;

import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availablecustomization")
public class AvailableCustomizationController {
    @Autowired
    AvailableCustomizationService availableCustomizationService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<AvailableCustomization>>> getAllCustomization(){
        List<AvailableCustomization> list= availableCustomizationService.getAllCustomization();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<AvailableCustomization>>> saveCustomization(@RequestBody AvailableCustomization availableCustomization){
        availableCustomizationService.saveAvailableCustomization(availableCustomization);
        List<AvailableCustomization> list= availableCustomizationService.getAllCustomization();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @PutMapping("/{id}")
    public ResponseEntity<List<ApiResponse<AvailableCustomization>>> updateCustomization(@PathVariable long id,@RequestBody AvailableCustomization availableCustomization){

        availableCustomizationService.updateAvailableCustomization(id,availableCustomization);
        List<AvailableCustomization> list= availableCustomizationService.getAllCustomization();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<AvailableCustomization>>> deleteCustomizationById(@PathVariable long id) {
        availableCustomizationService.deleteCustomizationById(id);
        List<AvailableCustomization> list = availableCustomizationService.getAllCustomization();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }

    }
