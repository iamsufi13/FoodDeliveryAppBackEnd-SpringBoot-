package com.whizFortuneRestaurant.Catlog1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatlogService {

    @Autowired
    private CatlogRepository catlogRepository;

    public Catlogs addCatlog(Catlogs catlogs) {
        catlogRepository.save(catlogs);
        return catlogs;
    }

    public List<Catlogs> getAllCatlog() {
        return catlogRepository.findAll();
    }

    public Catlogs getCatlogById(long id) {
        return catlogRepository.findById(id).orElse(null);
    }

    public Catlogs updateCatlog(long id, Catlogs updatedCatlog) {
        Optional<Catlogs> existingCatlogOpt = catlogRepository.findById(id);
        if (existingCatlogOpt.isPresent()) {
            Catlogs existingCatlog = existingCatlogOpt.get();
            existingCatlog.setName(updatedCatlog.getName());
            existingCatlog.setStatus(updatedCatlog.getStatus());
            existingCatlog.setIcon(updatedCatlog.getIcon());
            existingCatlog.setBannerImage(updatedCatlog.getBannerImage());
            return catlogRepository.save(existingCatlog);
        }
        return null;
    }

    public boolean deleteCatlogById(long id) {
        if (catlogRepository.existsById(id)) {
            catlogRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAll() {
        catlogRepository.deleteAll();
    }
}
