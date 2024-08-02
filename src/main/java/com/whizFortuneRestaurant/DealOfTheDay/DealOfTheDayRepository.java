package com.whizFortuneRestaurant.DealOfTheDay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealOfTheDayRepository extends JpaRepository<DealOfTheDay,Long> {
}
