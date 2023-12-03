package com.example.backend.use_case.file_download;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileDownloadService {
    ResponseEntity<byte[]> downloadFile(String claimNumber, String type);
}
