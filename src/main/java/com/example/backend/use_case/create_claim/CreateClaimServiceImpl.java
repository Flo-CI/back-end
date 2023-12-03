package com.example.backend.use_case.create_claim;

import com.example.backend.entities.Claim;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.response_model.CommonResponse;
import com.example.backend.response_model.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateClaimServiceImpl implements CreateClaimService {

    @Autowired
    ClaimFactory claimFactory;

    @Autowired
    UserRepository userRepository;

    @Override
    public CommonResponse<String> createClaim(String policyNumber, String type) {
        CommonResponse<String> response = new CommonResponse<>();
        Optional<User> optUser = userRepository.findByPolicyNumber(policyNumber);
        if (optUser.isEmpty()) {
            throw new GenericException("Invalid policy number");
        }
        User user = optUser.get();
        if (type.equals("Loss of Life Claim")) {
            Claim claim = claimFactory.createLossOfLifeClaim(policyNumber);
            user.addClaim(claim);
            userRepository.save(user);
            response.setStatus(200);
            response.setMessage("Loss of Life Claim " + claim.getClaimNumber() + " created successfully");
        } else {
            throw new GenericException("Invalid claim type");
        }
        return response;
    }
}
