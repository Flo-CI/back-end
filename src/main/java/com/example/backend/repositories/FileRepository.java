package com.example.backend.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository {
    byte[] downloadFileFromStorage(String fileName);

    String uploadFileToStorage(String blobName, byte[] file);
}
