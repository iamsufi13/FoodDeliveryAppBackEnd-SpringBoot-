package com.whizFortuneRestaurant.Cart;

import com.whizFortuneRestaurant.AvailableISizes.AvailableSizes;
import com.whizFortuneRestaurant.AvailableISizes.AvailableSizesService;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductService;
import com.whizFortuneRestaurant.Users.User;
import com.whizFortuneRestaurant.Users.UserService;
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
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @Autowired
    AvailableSizesService availableSizesService;

    @GetMapping
    public ResponseEntity<List<ApiResponse<Cart>>> getAllCartData(){
        List<Cart> list = cartService.getAllCart();
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));

    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ApiResponse<Cart>>> getSingleCart(@RequestParam long id){
        Cart list = cartService.getCartById(id);
        return ResponseEntity.ok().body(ResponseUtils.createResponse(list,"Success",true));
    }
    @PostMapping
    public ResponseEntity<List<ApiResponse<Cart>>> addCart(@RequestParam long userId,
                                                           @RequestParam long productId,
                                                           @RequestParam long sizeId,
                                                           @RequestParam int qty,
                                                           @RequestParam int price){
        Cart cart = new Cart();
        User user = userService.getUserById(userId);
        cart.setUser(user);
        Product product = productService.getProductById(productId);
        cart.setProduct(product);
        AvailableSizes availableSizes = availableSizesService.getSizeById(sizeId);
        cart.setAvailableSizes(availableSizes);
        cart.setQty(qty);
        cart.setPrice(price);
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
