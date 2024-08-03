package com.whizFortuneRestaurant.DealOfTheDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealOfTheDayService {
    @Autowired
    DealOfTheDayRepository dealOfTheDayRepository;

    public List<DealOfTheDayDto> getAllDealsOfTheDay() {
        List<DealOfTheDay> list = dealOfTheDayRepository.findAll();
        return list.stream().map(DealOfTheDayMapper::toDealOfTheDayDto).collect(Collectors.toList());
    }

    public DealOfTheDay addDealsOfTheDay(DealOfTheDay dealOfTheDay) {
        return dealOfTheDayRepository.save(dealOfTheDay);
    }
}
