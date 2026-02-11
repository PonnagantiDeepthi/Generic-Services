package com.dtt.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * The persistent class for the Consent database table.
 *
 */
@Entity
@Table(name="consent_history")
@NamedQuery(name="ConsentHistory.findAll", query="SELECT c FROM ConsentHistory c")
public class ConsentHistory implements  Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="consent_id")
    private Integer consentId;

    @Column(name="consent")
    private String consent;


    @Column(name="privacy_consent")
    private String privacyConsent;

    @Column(name="optional_terms_and_conditions")
    private String optionalTermsAndConditions;

    @Column(name="optional_data_and_privacy")
    private String optionalDataAndPrivacy;


    @Column(name="consent_type")
    private String consentType;

    @Column(name="consent_required")
    private boolean consentRequired;

    @Column(name="created_On")
    private String createdOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsentId() {
        return consentId;
    }

    public void setConsentId(Integer consentId) {
        this.consentId = consentId;
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

    public boolean isConsentRequired() {
        return consentRequired;
    }

    public void setConsentRequired(boolean consentRequired) {
        this.consentRequired = consentRequired;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "ConsentHistory{" +
                "id=" + id +
                ", consentId=" + consentId +
                ", consent='" + consent + '\'' +
                ", privacyConsent='" + privacyConsent + '\'' +
                ", optionalTermsAndConditions='" + optionalTermsAndConditions + '\'' +
                ", optionalDataAndPrivacy='" + optionalDataAndPrivacy + '\'' +
                ", consentType='" + consentType + '\'' +
                ", consentRequired=" + consentRequired +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
