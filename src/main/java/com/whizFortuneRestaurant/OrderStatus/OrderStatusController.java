package com.whizFortuneRestaurant.OrderStatus;

import com.whizFortuneRestaurant.Favorites.Favorite;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderstatus")
public class OrderStatusController {
    @Autowired
    OrderStatusService orderStatusService;
    @GetMapping
    public ResponseEntity<List<ApiResponse<OrderStatus>>> getAllOrderStatus(){
        List<OrderStatus> list = orderStatusService.getAllOrderStatus();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));
    }
    @GetMapping("/{id}")
    private ResponseEntity<List<ApiResponse<OrderStatus>>> getOrderStatusById(@PathVariable long id){
        OrderStatus list = orderStatusService.getOrderStatusById(id);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS",true));

    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<OrderStatus>>> addFavorite(@RequestBody OrderStatus orderStatus){
        System.out.println("detils to add " + orderStatus);
        orderStatusService.addOrderStatus(orderStatus);
        System.out.println("Added ");
        List<OrderStatus> list = orderStatusService.getAllOrderStatus();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderStatus> updatefavorite(@PathVariable long id, @RequestBody OrderStatus orderStatus) {
        System.out.println("updating  "+ orderStatus);
        OrderStatus updateOrderStatus = orderStatusService.updateOrderStatus(id, orderStatus);
        System.out.println("updated");
        return ResponseEntity.ok(updateOrderStatus);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<OrderStatus>>> deleteFavoriteById(@PathVariable long id){
        System.out.println("detils to add " + id);
        orderStatusService.deleteOrderStatusById(id);
        System.out.println("Deleted ");
        List<OrderStatus> list = orderStatusService.getAllOrderStatus();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }

}
