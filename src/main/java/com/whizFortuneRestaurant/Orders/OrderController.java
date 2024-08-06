package com.whizFortuneRestaurant.Orders;

import com.whizFortuneRestaurant.AvailableISizes.AvailableSizes;
import com.whizFortuneRestaurant.OrderStatus.OrderStatus;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Users.User;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<OrderDto>>> getAllOrders() {
        List<OrderDto> list = orderService.getAllOrders();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }

    @GetMapping("/details")
    public ResponseEntity<List<HashMap<String, Object>>> getOrderDetails(@RequestParam long id) {
        // Retrieve the product from the service
        Orders order = orderService.getByIdOrder(id);

        // Create a HashMap for the response
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "true");
        hashMap.put("data", order);
        hashMap.put("message", "SUCCESS");

        // Wrap the HashMap in a list
        List<HashMap<String, Object>> responseList = Collections.singletonList(hashMap);

        // Return the response list wrapped in ResponseEntity
        return ResponseEntity.ok(responseList);
    }

    @PostMapping
    public ResponseEntity<List<ApiResponse<Orders>>> addOrders(@RequestBody OrderDto orderDto) {
        Orders orders = convertToEntity(orderDto);

        orderService.addOrders(orderDto);

        List<OrderDto> list = orderService.getAllOrders();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "Success", true));
    }

    @PutMapping("/update")
    public ResponseEntity<Orders> updateOrders(@PathVariable long id,@RequestBody OrderDto orderDto) {
        Orders orders = convertToEntity(orderDto);

        Orders updatedOrder = orderService.updateOrders(id, orderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<ApiResponse<Orders>>> deleteOrderById(@RequestParam long id) {
        orderService.deleteByIdOrders(id);
        List<OrderDto> list = orderService.getAllOrders();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list, "SUCCESS", true));
    }

    private Orders convertToEntity(OrderDto orderDto) {
        Orders orders = new Orders();
        orders.setId(orderDto.getId()); // Ensure ID is set if needed for updates
        orders.setTxnid(orderDto.getTxnid());
        orders.setPrice(orderDto.getPrice());
        orders.setMrp(orderDto.getMrp());
        orders.setQty(orderDto.getQty());
        orders.setStatus(orderDto.getStatus());
        orders.setDiscountprice(orderDto.getDiscountPrice());

        List<Product> products = orderService.getProductsByIds(orderDto.getProductIds());
        orders.setProducts(products);

        List<AvailableSizes> availableSizes = orderService.getAvailableSizesByIds(orderDto.getAvailableSizeIds());
        orders.setAvailableSizes(availableSizes);

        OrderStatus orderStatus = orderService.getOrderStatusById(orderDto.getOrderStatusId());
        orders.setOrderStatus(orderStatus);

        User user = orderService.getUserById(orderDto.getUserId());
        orders.setUser(user);

        return orders;
    }
}


