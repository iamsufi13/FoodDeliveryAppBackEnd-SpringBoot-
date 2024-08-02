package com.whizFortuneRestaurant.AvailableCustomization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableCustomizationService {

    @Autowired
    AvailableCustomizationRepository availableCustomizationRepository;

    public List<AvailableCustomization> getAllCustomization() {
        return availableCustomizationRepository.findAll();
    }

    public void saveAvailableCustomization(AvailableCustomization availableCustomization) {
        availableCustomizationRepository.save(availableCustomization);
    }

    public void updateAvailableCustomization(long id,AvailableCustomization availableCustomization) {
        AvailableCustomization availableCustomization1 = availableCustomizationRepository.findById(id).orElse(null);
        if (availableCustomization1!=null){
            availableCustomization1.setStatus(availableCustomization.getStatus());
            availableCustomization1.setProduct(availableCustomization.getProduct());
            availableCustomization1.setCustomizationproduct(availableCustomization.getCustomizationproduct());
            availableCustomizationRepository.save(availableCustomization1);
        }

    }

    public void deleteCustomizationById(long id) {
        availableCustomizationRepository.deleteById(id);
    }
}
