package com.example.backend.interface_adapters;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.response_model.CommonListResponse;
import com.example.backend.response_model.CommonResponse;
import com.example.backend.response_model.FileModel;
import com.example.backend.use_case.claim_rank.ClaimRankService;

@RestController
@CrossOrigin("*")
public class ClaimRankController {
    @Autowired
    private ClaimRankService ClaimRankService;

    // TODO: Figure out what type to return
    @GetMapping("/claim/rank")
    public CommonResponse<HashMap<String, Integer>> getClaimRanks(@RequestParam String policyNumber) {
        return ClaimRankService.getClaimRanks(policyNumber);
    }

}
