package com.example.backend.use_case.claimFilesInfoFetch;

import com.example.backend.responsemodel.CommonListResponse;
import com.example.backend.responsemodel.FileModel;
import org.springframework.stereotype.Service;

@Service
public interface claimFilesInfoFetchService {
    public CommonListResponse<FileModel> getClaimInfo(String claimNumber, String policyNumber);
}
