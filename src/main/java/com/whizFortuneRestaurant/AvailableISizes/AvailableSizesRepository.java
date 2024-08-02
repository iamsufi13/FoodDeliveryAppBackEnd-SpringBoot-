package com.whizFortuneRestaurant.AvailableISizes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableSizesRepository extends JpaRepository<AvailableSizes, Long> {
}
