package com.example.service;

import com.example.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// 测试方法1 不启动spring容器，直接通过mock进行测试
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    // 注释部分 是 针对  UserServiceImpl 依赖了 DAO层的情况
//    @Mock
//    private UserRepository userRepository;

//    @InjectMocks
//    private UserServiceImpl userService;

    @Mock
    private UserServiceImpl userService;

    @Test
    void testGetUserById() {
//        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L, "Tom")));
        User user = new User();
        user.setName("test");
        when(userService.findById(1)).thenReturn(user);

        User result = userService.findById(1);
        assertEquals("test", result.getName());
    }

}