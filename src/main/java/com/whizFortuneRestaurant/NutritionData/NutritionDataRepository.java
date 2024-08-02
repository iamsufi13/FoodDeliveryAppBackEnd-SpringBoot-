package com.whizFortuneRestaurant.NutritionData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionDataRepository extends JpaRepository<NutritionData,Long> {
}
