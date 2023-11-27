package com.example.backend.use_case.user_login;

import com.example.backend.responsemodel.CommonResponse;
import com.example.backend.responsemodel.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserLoginService {
    public CommonResponse<String> userLogin(String policyNumber, String password);

    public CommonResponse<String> createUser(String policyNumber, String password);
}
