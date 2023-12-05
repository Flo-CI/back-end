package com.example.backend.use_case.claim_rank;

import java.util.Comparator;

import com.example.backend.response_model.ClaimBaseModel;

public class ClaimRankComparator implements Comparator<ClaimBaseModel> {
    // Compares claims by date created
    @Override
    public int compare(ClaimBaseModel o1, ClaimBaseModel o2) {
        return o1.getDateCreated().compareTo(o2.getDateCreated());
    }

}
