package com.example.backend.use_case.fileUpload;

import com.example.backend.responsemodel.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class fileUploadController {
    @Autowired
    private fileUploadService fileUploadService;

    @PostMapping("/upload")
    public CommonResponse<String> uploadFile(@RequestParam String claimNumber, @RequestParam String type, @RequestBody MultipartFile file){
        return fileUploadService.uploadFile(file, claimNumber, type);
    }
}
