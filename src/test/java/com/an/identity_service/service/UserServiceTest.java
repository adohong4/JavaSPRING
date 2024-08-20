package com.an.identity_service.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.an.identity_service.dto.request.UserCreationRequest;
import com.an.identity_service.dto.response.UserResponse;
import com.an.identity_service.entity.User;
import com.an.identity_service.exception.AppException;
import com.an.identity_service.repository.UserRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);

        request = UserCreationRequest.builder()
                .username("johnnguyen")
                .firstName("johnnguyen")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id(11)
                .username("johnnguyen")
                .firstName("johnnguyen")
                .lastName("Doe")
                .dob(dob)
                .build();

        user = User.builder()
                .id(11)
                .username("johnnguyen")
                .firstName("johnnguyen")
                .lastName("Doe")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GiVEN
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        // THEN
        Assertions.assertEquals(response.getId(), 11);
        Assertions.assertEquals(response.getUsername(), "johnnguyen");
    }

    @Test
    void createUser_userExist_fail() {
        // GiVEN
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(false);

        // WHEN
        var exception = Assertions.assertThrows(AppException.class, () -> userService.createUser(request));

        // THEN
        Assertions.assertEquals(exception.getErrorCode().getCode(), 1002);
    }
}
