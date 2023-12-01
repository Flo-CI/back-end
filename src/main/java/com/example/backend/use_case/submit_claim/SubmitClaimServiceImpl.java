package com.example.backend.use_case.submit_claim;

import com.example.backend.entities.Claim;
import com.example.backend.entities.Document;
import com.example.backend.entities.Form;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.repositories.UserRepository;
import com.example.backend.responsemodel.CommonResponse;
import com.example.backend.responsemodel.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmitClaimServiceImpl implements SubmitClaimService {
    @Autowired
    private ClaimRepository claimRepository;
    @Override
    public CommonResponse<String> submitClaim(String claimNumber) {
        CommonResponse<String> response = new CommonResponse<String>();
        // finding claim in database
        Claim claim = claimRepository.findByClaimNumber(claimNumber);
        // if claim is not found, return error
        if (claim == null) {
            throw new GenericException("Claim number " + claimNumber + " not found");
        }
        // checking all forms and documents uploaded
        for (Form form : claim.getForms()) {
            if (form.getFileUrl()!=null) {
                throw new GenericException("Form " + form.getFormName() + " not uploaded");
            }
        }
        for (Document document : claim.getDocuments()) {
            if (document.getFileUrl()!=null) {
                throw new GenericException("Form " + document.getDocumentName() + " not uploaded");
            }
        }
        // if all forms and documents are uploaded, set claim status to submitted
        claim.setStatus("Under Review");
        claimRepository.save(claim);
        response.setStatus(200);
        response.setMessage("Claim submitted successfully");
        return response;
    }
}
