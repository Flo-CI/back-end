package com.example.backend.use_case.claim_files_info_fetch;

import com.example.backend.entities.Claim;
import com.example.backend.entities.Document;
import com.example.backend.entities.Form;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.responsemodel.CommonListResponse;
import com.example.backend.responsemodel.FileModel;
import com.example.backend.responsemodel.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimFilesInfoFetchServiceImpl implements ClaimFilesInfoFetchService {

    @Autowired
    UserRepository userRepository;

    @Override
    public CommonListResponse<FileModel> getClaimInfo(String claimNumber, String policyNumber) {
        CommonListResponse<FileModel> commonResponse = new CommonListResponse<>();
        Optional<User> optUser = userRepository.findByPolicyNumber(policyNumber);
        if (optUser.isEmpty()) {
            throw new GenericException("Invalid policy number");
        }
        User user = optUser.get();
        Claim desiredClaim = null;
        for (Claim claim : user.getClaims()) {
            if (claim.getClaimNumber().equals(claimNumber)) {
                desiredClaim = claim;
                break;
            }
        }
        if (desiredClaim == null) {
            throw new GenericException("Invalid claim number");
        }
        convertToFileModel(desiredClaim, commonResponse);
        return commonResponse;
    }

    private void convertToFileModel(Claim claim, CommonListResponse<FileModel> response) {
        List<FileModel> files = new ArrayList<>();
        for (Form form : claim.getForms()) {
            FileModel fileModel = new FileModel();
            fileModel.setFileName(form.getFormName());
            fileModel.setFileType(form.getFormType());
            fileModel.setLastUpdated(form.getLastModified());
            files.add(fileModel);
        }
        for (Document document : claim.getDocuments()) {
            FileModel fileModel = new FileModel();
            fileModel.setFileName(document.getDocumentName());
            fileModel.setFileType(document.getDocumentType());
            fileModel.setLastUpdated(document.getLastModified());
            files.add(fileModel);
        }
        response.setDetails(files);
        response.setMessage("Displaying files for claim number: " + claim.getClaimNumber());
        response.setStatus(200);
        response.setDataCount(files.size());
    }
}
