package com.whizFortuneRestaurant.Catlog1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatlogRepository extends JpaRepository<Catlogs, Long> {

}
