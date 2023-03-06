package com.manager.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Builder
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private int age;

    public static UserDTO from(User user, int age) {
        return builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .birthday(user.getBirthday())
                .age(age)
                .build();
    }
}
