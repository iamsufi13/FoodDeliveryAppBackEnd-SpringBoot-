package com.whizFortuneRestaurant.Orders;

import com.whizFortuneRestaurant.Favorites.Favorite;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<Orders>>> getAllOrders(){
        List<Order> list = orderService.getAllOrders();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","ok"));

    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Orders>>> getByIdOrders(@PathVariable long id){
        Orders list= orderService.getByIdOrder(id);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"SUCCESS","true"));
    }

        @PostMapping
        public ResponseEntity<List<ApiResponse<Orders>>> addOrders(@RequestBody Orders orders){
        System.out.println("detils to add " + orders);
        orderService.addOrders(orders);
        System.out.println("Added ");
        List<Order> list = orderService.getAllOrders();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success","true"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrders(@PathVariable long id, @RequestBody Orders orders) {
        System.out.println("updating  "+ orders);
        Orders updateOrders = orderService.updateOrders(id, orders);
        System.out.println("updated");
        return ResponseEntity.ok(updateOrders);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Orders>>> deleteOrderById(@PathVariable long id){
        System.out.println("detils to add " + id);
        orderService.deleteByIdOrders(id);
        System.out.println("Deleted ");
        List<Order> list = orderService.getAllOrders();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success","true"));
    }

}
