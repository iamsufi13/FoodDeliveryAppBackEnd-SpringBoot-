package com.whizFortuneRestaurant.CustomizationProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizationProductRepository extends JpaRepository<CustomizationProduct,Long> {
}
