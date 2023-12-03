package com.example.backend.use_case.validate_form.form_validator;

import com.example.backend.entities.CertificationOfDeathForm;
import com.example.backend.response_model.FormError;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class CertificationOfDeathFormValidator implements FormValidator {
    CertificationOfDeathForm form;

    public CertificationOfDeathFormValidator(CertificationOfDeathForm form) {
        this.form = form;
    }

    @Override
    public void fillFormUsingOcr(HashMap<String, List<String>> formMap) {
        try {
            form.setNameDeceased(formMap.get("Full name of deceased").get(0));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
            try {
                form.setDateOfDeath(LocalDate.parse(formMap.get("Date of death (mo/day/yr)").get(0), formatter));
            } catch (Exception e) {
                log.info("Error in parsing date of death");
            }
            form.setImmediateCauseOfDeath(formMap.get("Immediate cause of death").get(0));
            form.setDueTo(formMap.get("Due to a consequence of").get(0));
            try {
                form.setDateOfSymptomsOnset(LocalDate.parse(
                        formMap.get("When did symptoms first appear or accident happen (mo/day/yr)").get(0),
                        formatter));

            } catch (Exception e) {
                log.info("Error in parsing date of symptoms onset");
            }
            if (formMap.get("Natural").get(0).equals(":selected:")) {
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
            if (formMap.get("Yes").get(0).equals(":selected:")) {
                form.setInquestPerformed(Boolean.TRUE);
            } else if (formMap.get("No").get(0).equals(":selected:")) {
                form.setInquestPerformed(Boolean.FALSE);
            }
            if (formMap.get("Yes").get(1).equals(":selected:")) {
                form.setAutopsyPerformed(Boolean.TRUE);
            } else if (formMap.get("No").get(1).equals(":selected:")) {
                form.setAutopsyPerformed(Boolean.FALSE);
            }
            form.setNameOfPhysician(formMap.get("Name").get(0));
            form.setTitleOfPhysician(formMap.get("Title").get(0));
            form.setAddressOfPhysician(formMap.get("Address (street, city, province, postal code)").get(0));
            form.setPhoneOfPhysician(formMap.get("Telephone number").get(0));
            try {
                form.setDateSigned(LocalDate.parse(formMap.get("Date (mo/day/yr)").get(0), formatter));
            } catch (Exception e) {
                log.info("Error in parsing date signed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FormError> validateForm() {
        return getFormErrors();
    }

    private List<FormError> getFormErrors() {
        List<FormError> errors = new ArrayList<>();
        log.info("" + errors.size());
        if (form.getNameDeceased() == null) {
            FormError error = new FormError("Full name of deceased", "Name of deceased cannot be null");
            errors.add(error);
        }
        if (form.getDateOfDeath() == null) {
            FormError error = new FormError("Date of death (mo/day/yr)",
                    "Date of death is either empty or in wrong format");
            errors.add(error);
        }
        if (form.getDateOfDeath().isAfter(LocalDate.now())) {
            FormError error = new FormError("Date of death (mo/day/yr)", "Date of death cannot be in the future");
            errors.add(error);
        }
        if (form.getImmediateCauseOfDeath() == null) {
            FormError error = new FormError("Immediate cause of death", "Immediate cause of death cannot be null");
            errors.add(error);
        }
        if (form.getDueTo() == null) {
            FormError error = new FormError("Due to a consequence of", "Due to a consequence of cannot be null");
            errors.add(error);
        }
        if (form.getDateOfSymptomsOnset() == null) {
            FormError error = new FormError("When did symptoms first appear or accident happen (mo/day/yr)",
                    "Date of symptoms onset is either empty or in wrong format");
            errors.add(error);
        }
        if (form.getDateOfSymptomsOnset().isAfter(LocalDate.now())) {
            FormError error = new FormError("When did symptoms first appear or accident happen (mo/day/yr)",
                    "Date of symptoms onset cannot be in the future. Please check date format.");
            errors.add(error);
        }
        if (form.getCauseOfDeath() == null) {
            FormError error = new FormError("Cause of death",
                    "Cause of death must be one of the 5 options. Please check 1 of them.");
            errors.add(error);
        }
        if (form.getPlaceOfDeath() == null) {
            FormError error = new FormError("Place of death (if institution or hospital, give name)",
                    "Place of death cannot be null");
            errors.add(error);
        }
        if (form.getInquestPerformed() == null) {
            FormError error = new FormError("Inquest performed",
                    "Inquest performed must be either Yes or No. Please check 1 of them.");
            errors.add(error);
        }
        if (form.getAutopsyPerformed() == null) {
            FormError error = new FormError("Autopsy performed",
                    "Autopsy performed must be either Yes or No. Please check 1 of them.");
            errors.add(error);
        }
        if (form.getNameOfPhysician() == null) {
            FormError error = new FormError("Name", "Name of physician cannot be null");
            errors.add(error);
        }
        if (form.getTitleOfPhysician() == null) {
            FormError error = new FormError("Title", "Title of physician cannot be null");
            errors.add(error);
        }
        if (form.getAddressOfPhysician() == null) {
            FormError error = new FormError("Address (street, city, province, postal code)",
                    "Address of physician cannot be null");
            errors.add(error);
        }
        if (form.getPhoneOfPhysician() == null) {
            FormError error = new FormError("Telephone number", "Phone of physician cannot be null");
            errors.add(error);
        }
        if (form.getDateSigned() == null) {
            FormError error = new FormError("Date (mo/day/yr)", "Date signed cannot be null");
            errors.add(error);
        }
        if (form.getDateSigned().isAfter(LocalDate.now())) {
            FormError error = new FormError("Date (mo/day/yr)", "Date signed cannot be in the future");
            errors.add(error);
        }
        return errors;
    }

    @Override
    public String getFormUrl() {
        return form.getFileUrl();
    }
}
