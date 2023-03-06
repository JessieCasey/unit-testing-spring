package com.manager;

import com.manager.SpringUnitTestingApplication;
import com.manager.entity.User;
import com.manager.entity.UserDTO;
import com.manager.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringUnitTestingApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void fetchUser_whenGetUserById_thenStatus200() throws Exception {
        long testId = 2;
        int age = 30;

        User expected = new User();
        expected.setId(testId);
        expected.setFirstname("Paul");
        expected.setLastname("Forward");
        expected.setBirthday(LocalDate.of(1990, 11, 15));

        Mockito.when(userService.getUserById(testId)).thenReturn(UserDTO.from(expected, age));

        mvc.perform(get("/api/users/" + testId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.firstname").value("Paul"))
                .andExpect(jsonPath("$.lastname").value("Forward"))
                .andExpect(jsonPath("$.birthday").value("1990-11-15"))
                .andExpect(jsonPath("$.age").value(age));

    }

    @Test
    public void fetchUser_whenGetUserWithBadId_thenStatus400() throws Exception {
        String testId = "Gdsa";

        mvc.perform(get("/api/users/" + testId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
