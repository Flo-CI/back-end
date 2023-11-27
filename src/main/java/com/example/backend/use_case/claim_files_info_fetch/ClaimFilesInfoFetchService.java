package com.example.backend.use_case.claim_files_info_fetch;

import com.example.backend.responsemodel.CommonListResponse;
import com.example.backend.responsemodel.FileModel;
import org.springframework.stereotype.Service;

@Service
public interface ClaimFilesInfoFetchService {
    public CommonListResponse<FileModel> getClaimInfo(String claimNumber, String policyNumber);
}
