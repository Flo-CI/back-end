package com.example.backend.use_case.ocr_generic;

import com.example.backend.entities.CertificationOfDeathForm;
import com.example.backend.entities.LifeClaimInformationRequestForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.azure.ai.formrecognizer.*;

import com.azure.ai.formrecognizer.documentanalysis.models.*;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClient;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClientBuilder;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.polling.SyncPoller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OcrForFormServiceImpl {
    private static final String key = "YOUR_KEY";
    private static final String endpoint = "YOUR_ENDPOINT";
    private static final String modelId = "prebuilt-document";

    public static List<DocumentKeyValuePair> performFormOcr(String documentUrl){
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

    public static HashMap<String, List<String>> getKeyValues(List<DocumentKeyValuePair> keyValuePairs){
        String[] keys = {"Fax","CLAIM NUMBER","Full name of deceased","Date of death (mo/day/yr)","Immediate cause of death","Due to a consequence of","When did symptoms first appear or accident happen (mo/day/yr)","Natural","Accident","Suicide","Homicide","Place of death (if institution or hospital, give name)","Yes","No","AM","PM","Undetermined","Yes","No","Name","Title","Address (street, city, province, postal code)","Telephone number","Signature","Date (mo/day/yr)"};
        HashMap<String, List<String>> formMap = new HashMap<>();
        for(DocumentKeyValuePair keyValuePair : keyValuePairs){
            String key = keyValuePair.getKey().getContent();
            String value = keyValuePair.getValue().getContent();
            if(!formMap.containsKey(key)){
                formMap.put(key, new ArrayList<>());
            }
            formMap.get(key).add(value);
        }
        return formMap;
    }

    public static void manipulateFormObject(CertificationOfDeathForm form, HashMap<String, List<String>> formMap){
        form.setNameDeceased(formMap.get("Full name of deceased").get(0));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        form.setDateOfDeath(LocalDate.parse(formMap.get("Date of death (mo/day/yr)").get(0), formatter));
        form.setImmediateCauseOfDeath(formMap.get("Immediate cause of death").get(0));
        form.setDueTo(formMap.get("Due to a consequence of").get(0));
        form.setDateOfSymptomsOnset(LocalDate.parse(formMap.get("When did symptoms first appear or accident happen (mo/day/yr)").get(0), formatter));
        if(formMap.get("Natural").get(0).equals(":selected:")){
            form.setCauseOfDeath("Natural");
        } else if (formMap.get("Accident").get(0).equals(":selected:")) {
            form.setCauseOfDeath("Accident");
        } else if (formMap.get("Suicide").get(0).equals(":selected:")) {
            form.setCauseOfDeath("Suicide");
        } else if (formMap.get("Homicide").get(0).equals(":selected:")) {
            form.setCauseOfDeath("Homicide");
        } else if (formMap.get("Undetermined").get(0).equals(":selected:")) {
            form.setCauseOfDeath("Undetermined");
        }
        form.setPlaceOfDeath(formMap.get("Place of death (if institution or hospital, give name)").get(0));
        if(formMap.get("Yes").get(0).equals(":selected:")){
            form.setInquestPerformed(Boolean.TRUE);
        } else if (formMap.get("No").get(0).equals(":selected:")) {
            form.setInquestPerformed(Boolean.FALSE);
        }
        if(formMap.get("Yes").get(1).equals(":selected:")){
            form.setAutopsyPerformed(Boolean.TRUE);
        } else if (formMap.get("No").get(1).equals(":selected:")) {
            form.setAutopsyPerformed(Boolean.FALSE);
        }
        form.setNameOfPhysician(formMap.get("Name").get(0));
        form.setTitleOfPhysician(formMap.get("Title").get(0));
        form.setAddressOfPhysician(formMap.get("Address (street, city, province, postal code)").get(0));
        form.setPhoneOfPhysician(formMap.get("Telephone number").get(0));
        form.setDateSigned(LocalDate.parse(formMap.get("Date (mo/day/yr)").get(0), formatter));
    }

    public static void main(String[] args){
        List<DocumentKeyValuePair> keyValuePairs = performFormOcr("https://ciflo.blob.core.windows.net/forms/Certification of Death - Physician Statement 1.pdf");
        HashMap<String, List<String>> formMap =getKeyValues(keyValuePairs);
        CertificationOfDeathForm form = new CertificationOfDeathForm();
        manipulateFormObject(form, formMap);
        System.out.println(form.getAddressOfPhysician());
    }
}
