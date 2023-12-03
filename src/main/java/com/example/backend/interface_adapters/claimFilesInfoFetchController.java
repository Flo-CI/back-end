package com.example.backend.interface_adapters;

import com.example.backend.response_model.CommonListResponse;
import com.example.backend.response_model.FileModel;
import com.example.backend.use_case.claim_files_info_fetch.ClaimFilesInfoFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ClaimFilesInfoFetchController {
    @Autowired
    private com.example.backend.use_case.claim_files_info_fetch.ClaimFilesInfoFetchService claimFilesInfoFetchService;

    @GetMapping("/claim/files")
    public CommonListResponse<FileModel> getClaimInfo(@RequestParam String claimNumber,
            @RequestParam String policyNumber) {
        return claimFilesInfoFetchService.getClaimInfo(claimNumber, policyNumber);
    }
}
