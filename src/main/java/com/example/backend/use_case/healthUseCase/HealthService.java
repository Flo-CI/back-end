package com.example.backend.use_case.healthUseCase;

import com.example.backend.responsemodel.CommonListResponse;
import org.springframework.stereotype.Service;

@Service
public interface HealthService {
    public CommonListResponse createDemoClaim(String policyNumber);

    public CommonListResponse getDemoClaim(String policyNumber);
}
