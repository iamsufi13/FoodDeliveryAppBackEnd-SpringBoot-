package com.whizFortuneRestaurant.ComboProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComboProductService {
    @Autowired
    ComboProductsReposiory comboProductsReposiory;

    public List<ComboProducts> getAllComboProducts() {
        return comboProductsReposiory.findAll();
    }
}
