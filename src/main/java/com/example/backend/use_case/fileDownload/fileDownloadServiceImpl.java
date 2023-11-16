package com.example.backend.use_case.fileDownload;

import com.example.backend.entities.Claim;
import com.example.backend.entities.Document;
import com.example.backend.entities.Form;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.repositories.FileRepository;
import com.example.backend.responsemodel.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class fileDownloadServiceImpl implements fileDownloadService{
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Override
    public ResponseEntity<byte[]> downloadFile(String claimNumber, String type) {
        Claim claim = claimRepository.findByClaimNumber(claimNumber);
        Form form = null;
        Document document = null;
        for(Form f: claim.getForms()){
            if(f.getFormType().equals(type)){
                form = f;
                break;
            }
        }
        for (Document d: claim.getDocuments()){
            if(d.getDocumentType().equals(type)){
                document = d;
                break;
            }
        }
        HttpHeaders headers = new HttpHeaders();
        if (form == null && document == null) {
            throw new GenericException("File not found");
        } else if (form != null) {
            String fileName = form.getFileUrl().substring(form.getFileUrl().lastIndexOf('/') + 1);
            byte[] data = fileRepository.downloadFileFromStorage(fileName);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + form.getFormName());
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(data);
        } else {
            String documentName = document.getFileUrl().substring(document.getFileUrl().lastIndexOf('/') + 1);
            byte[] data = fileRepository.downloadFileFromStorage(documentName);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + document.getDocumentName());
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(data);
        }
    }
}
