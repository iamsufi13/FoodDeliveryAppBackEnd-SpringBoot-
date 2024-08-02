package com.whizFortuneRestaurant.WishList;

import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductRepository;
import com.whizFortuneRestaurant.Users.User;
import com.whizFortuneRestaurant.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public void save(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public WishList add(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        WishList wishList= new WishList();
        wishList.setUser(user);
        wishList.setProduct(product);
        return wishListRepository.save(wishList);
    }
}
