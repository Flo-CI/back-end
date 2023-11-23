package com.example.backend.interface_adapters;

import com.example.backend.use_case.fileDownload.fileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class fileDownloadController {
    @Autowired
    private com.example.backend.use_case.fileDownload.fileDownloadService fileDownloadService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(String claimNumber, String type) {
        return fileDownloadService.downloadFile(claimNumber, type);
    }
}
