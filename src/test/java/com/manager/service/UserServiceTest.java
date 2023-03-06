package com.manager.service;

import com.manager.SpringUnitTestingApplication;
import com.manager.entity.User;
import com.manager.entity.UserDTO;
import com.manager.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = SpringUnitTestingApplication.class)
public class UserServiceTest {

    @Mock
    private User user;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @Transactional
    public void getUserById() {
        long testId = 2;

        User expected = new User();
        expected.setId(testId);
        expected.setFirstname("Ross");
        expected.setLastname("Geller");
        expected.setBirthday(LocalDate.of(1990, 11, 15));

        Mockito.when(userRepository.getReferenceById(testId)).thenReturn(expected);
        Mockito.when(userRepository.existsById(testId)).thenReturn(true);

        UserDTO actual = userService.getUserById(testId);

        assertEquals(actual.getFirstname(), expected.getFirstname());
        assertEquals(actual.getLastname(), expected.getLastname());
        assertEquals(actual.getBirthday(), expected.getBirthday());
        assertNotEquals(actual.getFirstname(), "Monica");
    }

    @Test
    public void getUserWithNotExistedId() {
        long testId = Long.MIN_VALUE;
        Throwable t = Assertions.assertThrows(EntityNotFoundException.class,
                () -> userService.getUserById(testId));

        assertEquals("The user with id: [" + testId + "] is not exist", t.getMessage());
    }

    @Test
    public void getUserAge() {
        Mockito.when(user.getBirthday()).thenReturn(LocalDate.of(1994, 10, 11));

        int expected = Period.between(user.getBirthday(), LocalDate.now()).getYears();

        assertEquals(userService.getAgeByUser(user), expected);
    }

    @Test
    public void getUserAgeByNullObject() {
        assertEquals(userService.getAgeByUser(null), -1);
    }
}
