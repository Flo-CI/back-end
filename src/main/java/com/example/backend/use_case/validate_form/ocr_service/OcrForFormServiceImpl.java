package com.example.backend.use_case.validate_form.ocr_service;

import com.example.backend.use_case.validate_form.form_validator.FormValidator;

import com.azure.ai.formrecognizer.documentanalysis.models.*;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClient;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClientBuilder;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.polling.SyncPoller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OcrForFormServiceImpl implements OcrFormService{
    private static final String key = "key";
    private static final String endpoint = "endpoint";
    private static final String modelId = "prebuilt-document";
    private final FormValidator formValidator;

    public OcrForFormServiceImpl(FormValidator formValidator){
        this.formValidator = formValidator;
    }

    private static List<DocumentKeyValuePair> performFormOcr(String documentUrl){
        // create your `DocumentAnalysisClient` instance and `AzureKeyCredential` variable
        DocumentAnalysisClient client = new DocumentAnalysisClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();

        SyncPoller < OperationResult, AnalyzeResult > analyzeDocumentPoller =
                client.beginAnalyzeDocumentFromUrl(modelId, documentUrl);

        AnalyzeResult analyzeResult = analyzeDocumentPoller.getFinalResult();

        analyzeResult.getKeyValuePairs().forEach(documentKeyValuePair -> {
            System.out.printf("Key content: %s%n", documentKeyValuePair.getKey().getContent());
            System.out.printf("Key content bounding region: %s%n",
                    documentKeyValuePair.getKey().getBoundingRegions().toString());

            if (documentKeyValuePair.getValue() != null) {
                System.out.printf("Value content: %s%n", documentKeyValuePair.getValue().getContent());
                System.out.printf("Value content bounding region: %s%n", documentKeyValuePair.getValue().getBoundingRegions().toString());
            }
        });
        return analyzeResult.getKeyValuePairs();
    }

    private static HashMap<String, List<String>> getKeyValues(List<DocumentKeyValuePair> keyValuePairs){
        String[] keys = {"Fax","CLAIM NUMBER","Full name of deceased","Date of death (mo/day/yr)","Immediate cause of death","Due to a consequence of","When did symptoms first appear or accident happen (mo/day/yr)","Natural","Accident","Suicide","Homicide","Place of death (if institution or hospital, give name)","Yes","No","AM","PM","Undetermined","Yes","No","Name","Title","Address (street, city, province, postal code)","Telephone number","Signature","Date (mo/day/yr)"};
        HashMap<String, List<String>> formMap = new HashMap<>();
        for(DocumentKeyValuePair keyValuePair : keyValuePairs){
            String key = keyValuePair.getKey().getContent();
            String value = null;
            if(keyValuePair.getValue()  != null){
                value = keyValuePair.getValue().getContent();
            }
            if(!formMap.containsKey(key)){
                formMap.put(key, new ArrayList<>());
            }
            formMap.get(key).add(value);
        }
        return formMap;
    }

    public void fillFormUsingOcr(){
        String documentUrl = formValidator.getFormUrl();
        List<DocumentKeyValuePair> documentKeyValuePairs = performFormOcr(documentUrl);
        HashMap<String, List<String>> formMap = getKeyValues(documentKeyValuePairs);
        formValidator.fillFormUsingOcr(formMap);
    }
}
