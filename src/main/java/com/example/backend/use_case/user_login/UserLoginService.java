package com.example.backend.use_case.user_login;

import com.example.backend.response_model.CommonResponse;
import com.example.backend.response_model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserLoginService {
    public CommonResponse<String> userLogin(String policyNumber, String password);

    public CommonResponse<String> createUser(String policyNumber, String password);
}
