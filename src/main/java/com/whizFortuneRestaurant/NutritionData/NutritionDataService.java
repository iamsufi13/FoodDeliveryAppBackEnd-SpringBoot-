package com.whizFortuneRestaurant.NutritionData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutritionDataService {
    @Autowired
    NutritionDataRepository nutritionDataRepository;



    public List<NutritionDataDto> getAllNutritionDataDto(){
        List<NutritionData> list= nutritionDataRepository.findAll();
        return list.stream().map(NutritionDataMapper::toNutritionDataDto).collect(Collectors.toList());
    }

    public void saveNutritionData(NutritionData nutritionData) {
        nutritionDataRepository.save(nutritionData);
    }

    public void updateNutritionData(long id, NutritionData nutritionData) {
        NutritionData nutritionData1 = nutritionDataRepository.findById(id).orElse(null);
        if (nutritionData1 != null) {
            nutritionData1.setFats(nutritionData.getFats());
            nutritionData1.setCalories(nutritionData.getCalories());
            nutritionData1.setProduct(nutritionData.getProduct());
            nutritionData1.setProteins(nutritionData.getProteins());
            nutritionData1.setStatus(nutritionData.getStatus());
            nutritionData1.setDt1(nutritionData.getDt1());
            nutritionDataRepository.save(nutritionData1);
        }
    }
    public void deleteNutritionData(long id) {
        nutritionDataRepository.deleteById(id);
    }

    public List<NutritionData> getAllNutritionData() {
        return nutritionDataRepository.findAll();
    }

}
