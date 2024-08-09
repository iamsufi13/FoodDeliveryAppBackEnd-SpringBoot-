package com.whizFortuneRestaurant.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmailId(String emailId);

    @Query("SELECT u FROM User u WHERE u.mobile = :mobile")
    User findByMobile(@Param("mobile") long mobile);
}
