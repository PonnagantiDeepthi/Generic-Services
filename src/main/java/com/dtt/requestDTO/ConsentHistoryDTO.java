package com.dtt.requestDTO;

import java.io.Serializable;

public class ConsentHistoryDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int id;
    private int consentId;
    private String optionalTermsAndConditions;
    private String optionalDataAndPrivacy;
    private String consentType;
    private Boolean consentRequired;
    private String consent;
    private String privacyConsent;

    private String createdOn;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsentId() {
        return consentId;
    }

    public void setConsentId(int consentId) {
        this.consentId = consentId;
    }

    public String getOptionalTermsAndConditions() {
        return optionalTermsAndConditions;
    }

    public void setOptionalTermsAndConditions(String optionalTermsAndConditions) {
        this.optionalTermsAndConditions = optionalTermsAndConditions;
    }

    public String getOptionalDataAndPrivacy() {
        return optionalDataAndPrivacy;
    }

    public void setOptionalDataAndPrivacy(String optionalDataAndPrivacy) {
        this.optionalDataAndPrivacy = optionalDataAndPrivacy;
    }

    public String getConsentType() {
        return consentType;
    }

    public void setConsentType(String consentType) {
        this.consentType = consentType;
    }

    public Boolean getConsentRequired() {
        return consentRequired;
    }

    public void setConsentRequired(Boolean consentRequired) {
        this.consentRequired = consentRequired;
    }

    public String getConsent() {
        return consent;
    }

    public void setConsent(String consent) {
        this.consent = consent;
    }

    public String getPrivacyConsent() {
        return privacyConsent;
    }

    public void setPrivacyConsent(String privacyConsent) {
        this.privacyConsent = privacyConsent;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "ConsentHistoryDTO{" +
                "id=" + id +
                ", consentId=" + consentId +
                ", optionalTermsAndConditions='" + optionalTermsAndConditions + '\'' +
                ", optionalDataAndPrivacy='" + optionalDataAndPrivacy + '\'' +
                ", consentType='" + consentType + '\'' +
                ", consentRequired=" + consentRequired +
                ", consent='" + consent + '\'' +
                ", privacyConsent='" + privacyConsent + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}

