package com.example.backend.interface_adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.use_case.file_download.FileDownloadService;

@RestController
@CrossOrigin(origins = "*")
public class FileDownloadController {
    @Autowired
    private com.example.backend.use_case.file_download.FileDownloadService fileDownloadService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(String claimNumber, String type) {
        return fileDownloadService.downloadFile(claimNumber, type);
    }
}
