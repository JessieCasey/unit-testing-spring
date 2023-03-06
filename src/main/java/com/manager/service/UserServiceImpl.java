package com.manager.service;

import com.manager.entity.User;
import com.manager.entity.UserDTO;
import com.manager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserById(Long id) {
        if (userRepository.existsById(id)) {
            User referenceById = userRepository.getReferenceById(id);

            return UserDTO.from(referenceById, getAgeByUser(referenceById));
        } else
            throw new EntityNotFoundException("The user with id: [" + id + "] " + "is not exist");
    }

    @Override
    public int getAgeByUser(User user) {
        return user != null ? Period.between(user.getBirthday(), LocalDate.now()).getYears() : -1;
    }
}
