package com.example.backend.use_case.fileDownload;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface fileDownloadService {
    ResponseEntity<byte[]> downloadFile(String claimNumber, String type);
}
