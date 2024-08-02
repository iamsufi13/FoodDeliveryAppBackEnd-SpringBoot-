package com.whizFortuneRestaurant.Cart;

import com.whizFortuneRestaurant.AvailableISizes.AvailableSizes;
import com.whizFortuneRestaurant.AvailableISizes.AvailableSizesRepository;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductRepository;
import com.whizFortuneRestaurant.Users.User;
import com.whizFortuneRestaurant.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AvailableSizesRepository availableSizesRepository;

    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public Cart getCartById(long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart addToCart(Cart cart) {
        validateReferences(cart);
        return cartRepository.save(cart);
    }

    public Cart updateCart(long id, Cart cart) {
        Cart existingCart = getCartById(id);
        if (existingCart != null) {
            existingCart.setQty(cart.getQty());
            existingCart.setUser(cart.getUser());
            existingCart.setPrice(cart.getPrice());
            existingCart.setProduct(cart.getProduct());
            existingCart.setAvailableSizes(cart.getAvailableSizes());
            validateReferences(existingCart);
            return cartRepository.save(existingCart);
        }
        return null;
    }

    public void deleteCartById(long id) {
        cartRepository.deleteById(id);
    }

    private void validateReferences(Cart cart) {
        if (cart.getUser() != null && cart.getUser().getUserid() != 0) {
            User user = userRepository.findById(cart.getUser().getUserid())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
            cart.setUser(user);
        }

        if (cart.getProduct() != null && cart.getProduct().getId() != 0) {
            Product product = productRepository.findById(cart.getProduct().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
            cart.setProduct(product);
        }

        if (cart.getAvailableSizes() != null && cart.getAvailableSizes().getId() != 0) {
            AvailableSizes availableSizes = availableSizesRepository.findById(cart.getAvailableSizes().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid size ID"));
            cart.setAvailableSizes(availableSizes);
        }
    }
}
