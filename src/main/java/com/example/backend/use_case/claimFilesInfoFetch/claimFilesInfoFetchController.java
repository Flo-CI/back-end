package com.example.backend.use_case.claimFilesInfoFetch;

import com.example.backend.responsemodel.CommonListResponse;
import com.example.backend.responsemodel.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class claimFilesInfoFetchController {
    @Autowired
    private claimFilesInfoFetchService claimFilesInfoFetchService;
    @GetMapping("/claim/files")
    public CommonListResponse<FileModel> getClaimInfo(@RequestParam String claimNumber, @RequestParam String policyNumber) {
        return claimFilesInfoFetchService.getClaimInfo(claimNumber, policyNumber);
    }
}
