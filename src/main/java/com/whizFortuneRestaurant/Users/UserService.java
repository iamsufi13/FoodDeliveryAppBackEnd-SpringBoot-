package com.whizFortuneRestaurant.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(long id, User user) {
        User user1 = getUserById(id);
        user1.setUsername(user.getUsername());
        user1.setAnniversarydate(user.getAnniversarydate());
        user1.setDob(user.getDob());
        user1.setDevicetype(user.getDevicetype());
        user1.setEmailId(user.getEmailId());
        user1.setGender(user.getGender());
        user1.setFcmtoken(user.getFcmtoken());
        user1.setMobile(user.getMobile());
        user1.setStatus(user.getStatus());
        // Update favorites collection
        user1.getFavorites().clear();
        if (user.getFavorites() != null) {
            user.getFavorites().forEach(favorite -> {
                favorite.setUser(user1); // Maintain the association
                user1.getFavorites().add(favorite);
            });
        }
        return userRepository.save(user1);

    }

    public void addUser(User user) {
        userRepository.save(user);

    }

    public void checkmobile(long mobile) {
        userRepository.findByMobile(mobile);
    }
}
