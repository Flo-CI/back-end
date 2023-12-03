package com.example.backend.use_case.health_use_case;

import com.example.backend.response_model.CommonListResponse;
import org.springframework.stereotype.Service;

@Service
public interface HealthService {
    public CommonListResponse createDemoClaim(String policyNumber);

    public CommonListResponse getDemoClaim(String policyNumber);
}
