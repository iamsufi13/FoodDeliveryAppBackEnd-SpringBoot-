package com.whizFortuneRestaurant.Cart;

import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Utils.ApiResponse;
import com.whizFortuneRestaurant.Utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<Cart>>> getAllCartData(){
        List<Cart> list = cartService.getAllCart();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));

    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Cart>>> getSingleCart(@PathVariable long id){
        Cart list = cartService.getCartById(id);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<Cart>>> addCart(@RequestBody Cart cart){
        System.out.println("detils to add to cart " + cart);
        cartService.addToCart(cart);
        System.out.println("Added to cart");
        List<Cart> list = cartService.getAllCart();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable long id, @RequestBody Cart cart) {
        System.out.println("updating cart "+ cart);
        Cart updatedCart = cartService.updateCart(id, cart);
        System.out.println("updated");
        return ResponseEntity.ok(updatedCart);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Cart>>> deleteCartById(@PathVariable long id){
        System.out.println("detils to delete to cart " + id);
        cartService.deleteCartById(id);
        System.out.println("Delete");
        List<Cart> list = cartService.getAllCart();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }
}
