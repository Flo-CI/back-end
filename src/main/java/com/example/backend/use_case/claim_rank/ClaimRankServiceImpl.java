package com.example.backend.use_case.claim_rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entities.Claim;
import com.example.backend.entities.User;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.repositories.UserRepository;
import com.example.backend.response_model.ClaimBaseModel;
import com.example.backend.response_model.CommonResponse;
import com.example.backend.response_model.GenericException;
import com.example.backend.Utils;

@Service
public class ClaimRankServiceImpl implements ClaimRankService {
    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommonResponse<HashMap<String, Integer>> getClaimRanks(String policyNumber) {
        CommonResponse<HashMap<String, Integer>> res = new CommonResponse<HashMap<String, Integer>>();

        // Get all claims and sort them by date
        List<ClaimBaseModel> allClaims = getAllRelevantClaims();
        Collections.sort(allClaims, new ClaimRankComparator());

        // Get user's claim numbers
        Set<String> userClaimNumbers = getUserClaimNumbers(policyNumber);

        // Compute ranks
        // Follows format policyNumber : rank
        HashMap<String, Integer> claimMap;
        claimMap = computeRanks(userClaimNumbers, allClaims);

        res.setDetails(claimMap);
        res.setMessage("Claim ranks fetched");
        res.setStatus(200);

        return res;
    }

    public HashMap<String, Integer> computeRanks(Set<String> userClaimNumbers, List<ClaimBaseModel> allClaims) {
        HashMap<String, Integer> rankMap = new HashMap<String, Integer>();

        // Loop through whole list of claims and check against user's claim numbers
        for (int i = 0; i < allClaims.size(); ++i) {
            String currentClaimNum = allClaims.get(i).getClaimNumber();
            // If the current index claim number is in the user claim number set
            if (userClaimNumbers.contains(currentClaimNum)) {
                rankMap.put(currentClaimNum, i + 1);
            }
        }

        return rankMap;
    }

    private List<ClaimBaseModel> getAllRelevantClaims() {
        List<ClaimBaseModel> claims = new ArrayList<>();

        for (Claim claim : claimRepository.findAll()) {
            ClaimBaseModel claimBaseModel = Utils.convertToClaimBaseModel(claim);
            // Only gets claims that are "Under Review"
            if (claimBaseModel.getStatus().equals("Under Review"))
                claims.add(claimBaseModel);

        }

        return claims;
    }

    private Set<String> getUserClaimNumbers(String policyNumber) {
        Optional<User> userOpt = userRepository.findByPolicyNumber(policyNumber);
        if (userOpt.isEmpty()) {
            throw new GenericException("user not found");
        }
        User user = userOpt.get();

        Set<String> claims = new HashSet<String>();
        for (Claim claim : user.getClaims()) {
            // Only gets claims that are "Under Review"
            if (claim.getStatus().equals("Under Review"))
                claims.add(claim.getClaimNumber());
        }

        return claims;
    }
}
