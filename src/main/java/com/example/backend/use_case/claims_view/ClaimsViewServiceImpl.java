package com.example.backend.use_case.claims_view;

import com.example.backend.entities.Claim;
import com.example.backend.entities.User;
import com.example.backend.repositories.DemoClaimRepository;
import com.example.backend.repositories.UserRepository;
import com.example.backend.response_model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimsViewServiceImpl implements ClaimsViewService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommonListResponse<ClaimBaseModel> getClaims(String policyNumber) {
        Optional<User> userOpt = userRepository.findByPolicyNumber(policyNumber);
        if (userOpt.isEmpty()) {
            throw new GenericException("user not found");
        }
        User user = userOpt.get();

        CommonListResponse<ClaimBaseModel> response = new CommonListResponse<>();
        List<ClaimBaseModel> claims = new ArrayList<>();
        for (Claim claim : user.getClaims()) {
            ClaimBaseModel claimBaseModel = convertToClaimBaseModel(claim);
            claims.add(claimBaseModel);
        }

        response.setDetails(claims);
        response.setMessage("Claims Fetched");
        response.setStatus(200);
        response.setDataCount(claims.size());
        return response;
    }

    private ClaimBaseModel convertToClaimBaseModel(Claim claim) {
        ClaimBaseModel claimBaseModel = new ClaimBaseModel();
        claimBaseModel.setClaimNumber(claim.getClaimNumber());
        claimBaseModel.setStatus(claim.getStatus());
        claimBaseModel.setType(claim.getType());
        claimBaseModel.setDateCreated(claim.getDateCreated());
        return claimBaseModel;
    }
}
