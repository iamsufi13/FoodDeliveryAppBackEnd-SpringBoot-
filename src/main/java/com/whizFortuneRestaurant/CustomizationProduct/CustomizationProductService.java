package com.whizFortuneRestaurant.CustomizationProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomizationProductService {
    @Autowired
    CustomizationProductRepository customizationProductRepository;

    public List<CustomizationProduct> getAll() {
        return customizationProductRepository.findAll();
    }

    public void saveCustomizationProduct(CustomizationProduct customizationProduct) {
        customizationProductRepository.save(customizationProduct);
    }

    public void updateCustomizationProduct(long id, CustomizationProduct customizationProduct) {
        CustomizationProduct customizationProduct1 = customizationProductRepository.findById(id).orElse(null);
        if (customizationProduct1 != null) {
            customizationProduct1.setName(customizationProduct.getName());
            customizationProduct1.setAvailableCustomizations(customizationProduct.getAvailableCustomizations());
            customizationProduct1.setStatus(customizationProduct.getStatus());
            customizationProduct1.setPrice(customizationProduct.getPrice());
            customizationProduct1.setQuantity(customizationProduct.getQuantity());
            customizationProduct1.setDt1(customizationProduct.getDt1());
            customizationProductRepository.save(customizationProduct1);
        }

    }

    public void deleteCustomiationProductById(long id){
        customizationProductRepository.deleteById(id);
    }

}