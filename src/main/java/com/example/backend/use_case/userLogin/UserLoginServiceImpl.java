package com.example.backend.use_case.userLogin;

import com.example.backend.entities.Claim;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.responsemodel.ClaimBaseModel;
import com.example.backend.responsemodel.CommonResponse;
import com.example.backend.responsemodel.GenericException;
import com.example.backend.responsemodel.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public CommonResponse<UserModel> userLogin(String policyNumber, String password) {
        if(policyNumber.isEmpty() || password.isEmpty()){
            throw new GenericException("enter policy number and password");
        }
        Optional<User> userOpt = userRepository.findByPolicyNumber(policyNumber);
        if(userOpt.isEmpty()){
            throw new GenericException("user not found");
        }
        User user = userOpt.get();
        if(!user.getPassword().equals(password)) {
            throw new GenericException("user not found");
        }
        CommonResponse<UserModel> response = new CommonResponse<>();
        UserModel userModel = convertToUserModel(user);
        response.setDetails(userModel);
        response.setMessage("User Login Successful");
        response.setStatus(200);
        return response;
    }

    @Override
    public CommonResponse<String> createUser(String policyNumber, String password) {
        if(policyNumber.isEmpty() || password.isEmpty()){
            throw new GenericException("enter policy number and password");
        }
        Optional<User> userOpt = userRepository.findByPolicyNumber(policyNumber);
        if(userOpt.isPresent()){
            throw new GenericException("user already exists");
        }
        User user = new User();
        user.setPolicyNumber(policyNumber);
        user.setPassword(password);
        userRepository.save(user);
        CommonResponse<String> response = new CommonResponse<>();
        response.setMessage("User Created");
        response.setStatus(201);
        return response;
    }

    private UserModel convertToUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setPolicyNumber(user.getPolicyNumber());
        List<ClaimBaseModel> claimBaseModels = new ArrayList<>();
        for(Claim claim : user.getClaims()){
            claimBaseModels.add(convertToClaimBaseModel(claim));
        }
        userModel.setClaims(claimBaseModels);
        return userModel;
    }

    private ClaimBaseModel convertToClaimBaseModel(Claim claim){
        ClaimBaseModel claimBaseModel = new ClaimBaseModel();
        claimBaseModel.setClaimNumber(claim.getClaimNumber());
        claimBaseModel.setStatus(claim.getStatus());
        claimBaseModel.setType(claim.getType());
        claimBaseModel.setDateCreated(claim.getDateCreated());
        return claimBaseModel;
    }
}
