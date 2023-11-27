package com.example.backend.use_case.file_upload;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.example.backend.entities.Claim;
import com.example.backend.entities.Document;
import com.example.backend.entities.Form;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.repositories.DocumentRepository;
import com.example.backend.repositories.FileRepository;
import com.example.backend.repositories.FormRepository;
import com.example.backend.responsemodel.CommonResponse;
import com.example.backend.responsemodel.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private FormRepository<Form> formRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private FileRepository fileRepository;

    public CommonResponse<String> uploadFile(MultipartFile file, String claimNumber, String type) {
        CommonResponse<String> response = new CommonResponse<>();
        Claim claim = claimRepository.findByClaimNumber(claimNumber);
        if (claim == null) {
            throw new GenericException("Invalid claim number");
        }
        String blobName = claimNumber + new Random().nextInt(100000, 999999) + type + ".pdf";
        try {
            byte[] data = file.getBytes();
            Form form = null;
            Document document = null;
            for (Form f : claim.getForms()) {
                if (f.getFormType().equals(type)) {
                    form = f;
                    break;
                }
            }
            for (Document d : claim.getDocuments()) {
                if (d.getDocumentType().equals(type)) {
                    document = d;
                    break;
                }
            }
            if (form == null && document == null) {
                throw new GenericException("File not found");
            } else if (form != null) {
                String fileUrl = fileRepository.uploadFileToStorage(blobName, data);
                form.setFormName(file.getOriginalFilename());
                form.setFileUrl(fileUrl);
                form.setLastModified(LocalDate.now());
                formRepository.save(form);
            } else {
                String fileUrl = fileRepository.uploadFileToStorage(blobName, data);
                document.setDocumentName(file.getOriginalFilename());
                document.setFileUrl(fileUrl);
                document.setLastModified(LocalDate.now());
                documentRepository.save(document);
            }
        } catch (IOException e) {
            throw new GenericException("Error uploading file");
        }
        response.setMessage("File uploaded successfully");
        response.setStatus(200);
        return response;
    }
}
