package com.whizFortuneRestaurant.ComboProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComboProductService {
    @Autowired
    ComboProductsReposiory comboProductsReposiory;

    public List<ComboProductsDto> getAllComboProducts() {
        List<ComboProducts> list= comboProductsReposiory.findAll();
        return list.stream().map(ComboProductsMapper::toComboProductsDto).collect(Collectors.toList());
    }

    public ComboProducts findById(long id) {
        return comboProductsReposiory.findById(id).orElse(null);
    }
}
