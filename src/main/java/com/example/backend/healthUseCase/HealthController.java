package com.example.backend.healthUseCase;

import com.example.backend.responsemodel.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @GetMapping("/health")
    public String health() {
        return "Hello World!";
    }

    @PostMapping("/demo/claim")
    public CommonResponse createDemoClaim(@RequestParam(name = "policyNumber") String policyNumber) {
        log.info(policyNumber);
        return healthService.createDemoClaim(policyNumber);
    }

    @GetMapping("/demo/claim")
    public CommonResponse getDemoClaim(@RequestParam(name = "policyNumber") String policyNumber) {
        return healthService.getDemoClaim(policyNumber);
    }
}
