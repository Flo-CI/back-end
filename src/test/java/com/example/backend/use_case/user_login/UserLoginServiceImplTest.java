package com.example.backend.use_case.user_login;

import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.response_model.CommonResponse;
import com.example.backend.response_model.GenericException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserLoginServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserLoginServiceImpl userLoginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUserLoginSuccess() {
        // Mock data
        String policyNumber = "123";
        String password = "password";
        User user = new User();
        user.setPolicyNumber(policyNumber);
        user.setPassword(password);

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(user));

        // Perform the action
        CommonResponse<String> response = userLoginService.userLogin(policyNumber, password);

        // Verify interactions and assertions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
        assertNotNull(response);
        assertEquals("User Login Successful", response.getMessage());
        assertEquals(200, response.getStatus());
    }

    @Test
    void testUserLoginUserNotFound() {
        // Mock data
        String policyNumber = "456";
        String password = "password";

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.empty());

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> userLoginService.userLogin(policyNumber, password));

        // Verify interactions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
    }

    @Test
    void testUserLoginIncorrectPassword() {
        // Mock data
        String policyNumber = "789";
        String password = "incorrectPassword";
        User user = new User();
        user.setPolicyNumber(policyNumber);
        user.setPassword("correctPassword"); // Different password

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(user));

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> userLoginService.userLogin(policyNumber, password));

        // Verify interactions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
    }

    @Test
    void testCreateUserSuccess() {
        // Mock data
        String policyNumber = "123";
        String password = "password";

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(new User());

        // Perform the action
        CommonResponse<String> response = userLoginService.createUser(policyNumber, password);

        // Verify interactions and assertions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
        verify(userRepository, times(1)).save(any());
        assertNotNull(response);
        assertEquals("User Created", response.getMessage());
        assertEquals(201, response.getStatus());
    }

    @Test
    void testCreateUserUserAlreadyExists() {
        // Mock data
        String policyNumber = "456";
        String password = "password";
        User existingUser = new User();
        existingUser.setPolicyNumber(policyNumber);

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(existingUser));

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> userLoginService.createUser(policyNumber, password));

        // Verify interactions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
        verify(userRepository, never()).save(any());
    }

    // Add more test cases as needed
}
