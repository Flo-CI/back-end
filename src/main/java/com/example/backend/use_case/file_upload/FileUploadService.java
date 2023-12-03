package com.example.backend.use_case.file_upload;

import com.example.backend.response_model.CommonResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileUploadService {
    public CommonResponse<String> uploadFile(MultipartFile file, String claimNumber, String type);

}
