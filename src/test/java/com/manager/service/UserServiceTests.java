package com.manager.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    List mockList;

    @Test
    public void whenMockAnnotation () {
        //создаем правило: вернуть 10 при вызове метода size
        Mockito.doReturn(10).when(mockList).size();

        //тут вызывается метод и вернет 10!!
        assertEquals(10, mockList.size());
    }
}
