package com.example.backend.use_case.healthUseCase;

import com.example.backend.entities.DemoClaim;
import com.example.backend.repositories.DemoClaimRepository;
import com.example.backend.responsemodel.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    private DemoClaimRepository demoClaimRepository;

    @Override
    public CommonResponse createDemoClaim(String policyNumber) {
        CommonResponse response = new CommonResponse();
        DemoClaim demoClaim = new DemoClaim();
        demoClaim.setPolicyNumber(policyNumber);
        demoClaimRepository.save(demoClaim);
        response.setMessage("Demo Claim Created");
        response.setStatus(200);
        return response;
    }

    @Override
    public CommonResponse getDemoClaim(String policyNumber) {
        try {
            CommonResponse response = new CommonResponse();
            response.setDetails(demoClaimRepository.findByPolicyNumber(policyNumber));
            response.setMessage("Demo Claim Found");
            response.setStatus(200);
            return response;
        } catch (Exception e) {
            CommonResponse response = new CommonResponse();
            response.setMessage("Demo Claim Not Found");
            response.setStatus(404);
            return response;
        }
    }
}
