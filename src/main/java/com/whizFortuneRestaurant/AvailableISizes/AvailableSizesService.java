package com.whizFortuneRestaurant.AvailableISizes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailableSizesService {
    @Autowired
    AvailableSizesRepository availableSizesRepository;

    public List<AvailableSizes> getAllSizes() {
    return availableSizesRepository.findAll();
    }
    public List<AvailableSizesDto> getAllSizesDto(){
        List<AvailableSizes> list = availableSizesRepository.findAll();
        return list.stream().map(AvailableSizesMapper::toAvailableSizesDto).collect(Collectors.toList());
    }

    public void saveAvailableSize(AvailableSizes availableSize) {
        availableSizesRepository.save(availableSize);
    }

    public void updateAvailableSize(long id, AvailableSizes availableSize) {
        AvailableSizes availableSizes1= availableSizesRepository.findById(id).orElse(null);
        if (availableSizes1 !=null){
            availableSizes1.setSize_name(availableSize.getSize_name());
            availableSizes1.setStatus(availableSize.getStatus());
            availableSizes1.setPrice(availableSize.getPrice());
            availableSizes1.setProduct(availableSizes1.getProduct());
            availableSizesRepository.save(availableSizes1);

        }
    }
    public void deleteAvailableSizesById(long id){
        availableSizesRepository.deleteById(id);
    }

    public AvailableSizes getSizeById(long sizeId) {
        return availableSizesRepository.findById(sizeId).orElse(null);
    }
}
