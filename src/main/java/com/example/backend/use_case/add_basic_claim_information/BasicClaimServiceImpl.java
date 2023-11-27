package com.example.backend.use_case.add_basic_claim_information;

import com.example.backend.entities.User;
import com.example.backend.entities.Claim;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.repositories.UserRepository;
import com.example.backend.requestmodel.BasicClaimModel;
import com.example.backend.responsemodel.CommonResponse;
import com.example.backend.responsemodel.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BasicClaimServiceImpl implements BasicClaimService {
    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommonResponse<String> addBasicClaimInformation(BasicClaimModel basicClaimModel) {
        CommonResponse<String> response = new CommonResponse<>();
        Optional<User> userOpt = userRepository.findByPolicyNumber(basicClaimModel.getPolicyNumber());
        if (userOpt.isEmpty()) {
            throw new GenericException("user not found");
        }
        User user = userOpt.get();
        Claim claim = new Claim();
        claim.setClaimNumber(basicClaimModel.getClaimNumber());
        claim.setType(basicClaimModel.getType());
        claim.setStatus(basicClaimModel.getStatus());
        claim.setDateCreated(LocalDate.parse(basicClaimModel.getDateCreated()));
        user.addClaim(claim);
        claimRepository.save(claim);
        userRepository.save(user);
        response.setMessage("Claim created");
        response.setStatus(201);
        return response;
    }
}
