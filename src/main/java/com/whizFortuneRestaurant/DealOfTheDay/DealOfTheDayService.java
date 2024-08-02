package com.whizFortuneRestaurant.DealOfTheDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealOfTheDayService {
    @Autowired
    DealOfTheDayRepository dealOfTheDayRepository;

    public List<DealOfTheDay> getAllDealsOfTheDay() {
        return dealOfTheDayRepository.findAll();
    }

    public DealOfTheDay addDealsOfTheDay(DealOfTheDay dealOfTheDay) {
        return dealOfTheDayRepository.save(dealOfTheDay);
    }
}
