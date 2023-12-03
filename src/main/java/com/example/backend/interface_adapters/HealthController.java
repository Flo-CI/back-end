package com.example.backend.interface_adapters;

import com.example.backend.response_model.CommonListResponse;
import com.example.backend.use_case.health_use_case.HealthService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CommonListResponse createDemoClaim(@RequestParam(name = "policyNumber") String policyNumber) {
        log.info(policyNumber);
        return healthService.createDemoClaim(policyNumber);
    }

    @GetMapping("/demo/claim")
    public CommonListResponse getDemoClaim(@RequestParam(name = "policyNumber") String policyNumber) {
        return healthService.getDemoClaim(policyNumber);
    }
}
