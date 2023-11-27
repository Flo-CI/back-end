package com.example.backend.use_case.health_use_case;

import com.example.backend.entities.DemoClaim;
import com.example.backend.repositories.DemoClaimRepository;
import com.example.backend.responsemodel.CommonListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    private DemoClaimRepository demoClaimRepository;

    @Override
    public CommonListResponse createDemoClaim(String policyNumber) {
        CommonListResponse response = new CommonListResponse();
        DemoClaim demoClaim = new DemoClaim();
        demoClaim.setPolicyNumber(policyNumber);
        demoClaimRepository.save(demoClaim);
        response.setMessage("Demo Claim Created");
        response.setStatus(200);
        return response;
    }

    @Override
    public CommonListResponse getDemoClaim(String policyNumber) {
        try {
            CommonListResponse response = new CommonListResponse();
            response.setDetails(demoClaimRepository.findByPolicyNumber(policyNumber));
            response.setMessage("Demo Claim Found");
            response.setStatus(200);
            return response;
        } catch (Exception e) {
            CommonListResponse response = new CommonListResponse();
            response.setMessage("Demo Claim Not Found");
            response.setStatus(404);
            return response;
        }
    }
}
