package com.kjawank_jose.lds_scriptures_game.service;

import com.kjawank_jose.lds_scriptures_game.model.User;
import com.kjawank_jose.lds_scriptures_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findOrCreatedUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            userRepository.save(user);
        }
    return user;
    }
}
