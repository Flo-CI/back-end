package com.example.backend.interface_adapters;

import com.example.backend.responsemodel.CommonResponse;
import com.example.backend.use_case.file_upload.FileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @Autowired
    private com.example.backend.use_case.file_upload.FileUploadService fileUploadService;

    @PostMapping("/upload")
    public CommonResponse<String> uploadFile(@RequestParam String claimNumber, @RequestParam String type,
            @RequestBody MultipartFile file) {
        return fileUploadService.uploadFile(file, claimNumber, type);
    }
}
