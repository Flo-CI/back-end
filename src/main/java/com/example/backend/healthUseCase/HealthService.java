package com.example.backend.healthUseCase;

import com.example.backend.responsemodel.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface HealthService {
    public CommonResponse createDemoClaim(String policyNumber);

    public CommonResponse getDemoClaim(String policyNumber);
}
