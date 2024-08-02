package com.whizFortuneRestaurant.AvailableCustomization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableCustomizationRepository extends JpaRepository<AvailableCustomization,Long> {
}
