package com.example.backend.interface_adapters;

import com.example.backend.responsemodel.CommonResponse;
import com.example.backend.use_case.fileUpload.fileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class fileUploadController {
    @Autowired
    private com.example.backend.use_case.fileUpload.fileUploadService fileUploadService;

    @PostMapping("/upload")
    public CommonResponse<String> uploadFile(@RequestParam String claimNumber, @RequestParam String type, @RequestBody MultipartFile file){
        return fileUploadService.uploadFile(file, claimNumber, type);
    }
}
