package com.whizFortuneRestaurant.AvailableISizes;

import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availablesizes")
public class AvailableSizesController {

    @Autowired
    AvailableSizesService availableSizesService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<AvailableSizes>>> getAllAvailableSizes(){
        List<AvailableSizes> list =availableSizesService.getAllSizes();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));

    }

    @GetMapping("/general")
    public List<AvailableSizesDto> getAllAvailableSizesDto(){
        return availableSizesService.getAllSizesDto();

    }


    @PostMapping
    public ResponseEntity<List<ApiResponse<AvailableSizes>>> saveAvailableSize(@RequestBody AvailableSizes availableSize){
        availableSizesService.saveAvailableSize(availableSize);
        List<AvailableSizes> list =availableSizesService.getAllSizes();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));

    }
    @PutMapping("/{id}")
    public ResponseEntity<List<ApiResponse<AvailableSizes>>> updateAvailableSize(@PathVariable long id,@RequestBody AvailableSizes availableSize){
        System.out.println("data for updating "+availableSize);
        availableSizesService.updateAvailableSize(id,availableSize);
        List<AvailableSizes> list =availableSizesService.getAllSizes();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<AvailableSizes>>> deleteAvailableSize(@PathVariable long id){
        availableSizesService.deleteAvailableSizesById(id);
        List<AvailableSizes> list =availableSizesService.getAllSizes();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));

    }
}
