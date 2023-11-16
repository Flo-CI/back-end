package com.example.backend.use_case.fileUpload;

import com.example.backend.responsemodel.CommonResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface fileUploadService {
    public CommonResponse<String> uploadFile(MultipartFile file, String claimNumber, String type);

}
