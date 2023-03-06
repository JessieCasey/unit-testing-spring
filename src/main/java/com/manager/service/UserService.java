package com.manager.service;

import com.manager.entity.User;
import com.manager.entity.UserDTO;

public interface UserService {
    UserDTO getUserById(Long id);

    int getAgeByUser(User user);
}
