package com.example.backend.interface_adapters;

import com.example.backend.responsemodel.CommonResponse;
import com.example.backend.responsemodel.UserModel;
import com.example.backend.use_case.user_login.UserLoginService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    @GetMapping("user/login")
    public CommonResponse<String> userLogin(@RequestParam(value = "policyNumber") String policyNumber,
            @RequestParam(value = "password") String password) {

        return userLoginService.userLogin(policyNumber, password);
    }

    @PostMapping("user/create")
    public CommonResponse<String> createUser(@RequestParam(value = "policyNumber") String policyNumber,
            @RequestParam(value = "password") String password) {
        return userLoginService.createUser(policyNumber, password);
    }
}
