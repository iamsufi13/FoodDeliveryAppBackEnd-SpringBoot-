package com.whizFortuneRestaurant.Product;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final String STATIC_TAG = "speciality";

    public List<ProductDto> getAllProducts() {
        List<Product> list = productRepository.findAll();
        return list.stream().map(ProductMapper::toProductDto).collect(Collectors.toList());
    }
    public List<ProductListingDto> getAllProductsExcludeOtherEntities(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::toProductListingDto).collect(Collectors.toList());
    }
    public List<ProductByTagDto> getProductsBySpeciality() {
        List<Product> list= productRepository.findProductsBySellerTag("specialty");
        return list.stream().map(ProductMapper::toProductByTagDto).collect(Collectors.toList());
    }

    public Product addProduct(Product product) {
        System.out.println(product.getCatlogs());
        return productRepository.save(product);
    }

    public Product getProductById(long id) {
       return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product updateProduct(long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setFoodType(product.getFoodType());
        existingProduct.setMrp(product.getMrp());
        existingProduct.setCustomizationAvailable(product.getCustomizationAvailable());
        existingProduct.setImage(product.getImage());
        existingProduct.setServeFor(product.getServeFor());
        existingProduct.setSellerTags(product.getSellerTags());
        existingProduct.setCustomizationStatus(product.getCustomizationStatus());
        existingProduct.setNutritionStatus(product.getNutritionStatus());
        existingProduct.setSpicyStatus(product.getSpicyStatus());
        existingProduct.setFreeDeliveryStatus(product.getFreeDeliveryStatus());
        existingProduct.setSellPrice(product.getSellPrice());
        existingProduct.setOnionGarlicStatus(product.getOnionGarlicStatus());
        existingProduct.setCatlogs(product.getCatlogs()); // Update Catlogs
        // Add or update other related entities if needed
        return productRepository.save(existingProduct);
    }

    public void deleteProductById(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }
    }

    public List<ProductByTagDto> getProductsByTag(String tag) {
        List<Product> list = productRepository.findByTag(tag);
        return list.stream().map(ProductMapper::toProductByTagDto).collect(Collectors.toList());
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }


    public List<ProductListingDto> getProductsByCategory(long id) {
        List<Product> list = productRepository.findByCategoryId(id);
        return list.stream().map(ProductMapper::toProductListingDto).collect(Collectors.toList());
    }
}

