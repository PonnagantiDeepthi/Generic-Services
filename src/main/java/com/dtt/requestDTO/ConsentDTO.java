package com.dtt.requestDTO;

import java.io.Serializable;

public class ConsentDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer consentId;

	private String consent;

	private String createdOn;

	private String updatedOn;

	private String consentType;

	private String status;

	private String privacyConsent;

	private Boolean consentRequired;

	private String optionalTermsAndConditions;

	private String optionalDataAndPrivacy;

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

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getConsentType() {
		return consentType;
	}

	public void setConsentType(String consentType) {
		this.consentType = consentType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrivacyConsent() {
		return privacyConsent;
	}

	public void setPrivacyConsent(String privacyConsent) {
		this.privacyConsent = privacyConsent;
	}

	public Boolean getConsentRequired() {
		return consentRequired;
	}

	public void setConsentRequired(Boolean consentRequired) {
		this.consentRequired = consentRequired;
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

	@Override
	public String toString() {
		return "ConsentDTO{" +
				"consentId=" + consentId +
				", consent='" + consent + '\'' +
				", createdOn='" + createdOn + '\'' +
				", updatedOn='" + updatedOn + '\'' +
				", consentType='" + consentType + '\'' +
				", status='" + status + '\'' +
				", privacyConsent='" + privacyConsent + '\'' +
				", consentRequired=" + consentRequired +
				", optionalTermsAndConditions='" + optionalTermsAndConditions + '\'' +
				", optionalDataAndPrivacy='" + optionalDataAndPrivacy + '\'' +
				'}';
	}
}

