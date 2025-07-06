
package com.example.service;

import com.example.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        User user = new User();
        user.setId(1);
        return user;
    }
}
