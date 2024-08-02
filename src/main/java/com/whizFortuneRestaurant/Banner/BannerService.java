package com.whizFortuneRestaurant.Banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {
    @Autowired
    BannerRepository bannerRepository;

    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }
    public Banner addBanner(Banner banner){
       return bannerRepository.save(banner);
    }

    public Banner getBannerById(long id) {
        return bannerRepository.findById(id).orElse(null);
    }

    public void updateBanner(long id, Banner banner) {
        Banner banner1 = getBannerById(id);
        System.out.println(banner1.toString());
        banner1.setImage(banner.getImage());
        banner1.setPlatformtype(banner.getPlatformtype());
        banner1.setStatus(banner.getStatus());
        bannerRepository.save(banner1);
    }

    public void deleteBannerById(long id) {
        bannerRepository.deleteById(id);
    }

    public List<Banner> getAllBannersGeneral() {
        return bannerRepository.findAll();
    }
}
