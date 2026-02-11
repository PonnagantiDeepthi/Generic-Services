package com.dtt.requestDTO;

public class SubscriberDetailsDto {

	private String subscriberUid;
	private String fullName;
	private String dateOfBirth;
	private String mobileNumber;
	private String emailId;
	private String createdOn;
	private String selfieUri;
	private String gender;
	private String onboardingDataFieldsJson;
	private String selfie;

	public SubscriberDetailsDto(String subscriberUid, String fullName, String dateOfBirth, String mobileNumber,
			String emailId, String createdOn, String selfieUri, String gender, String onboardingDataFieldsJson,String selfie) {
		this.subscriberUid = subscriberUid;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.createdOn = createdOn;
		this.selfieUri = selfieUri;
		this.gender = gender;
		this.onboardingDataFieldsJson = onboardingDataFieldsJson;
		this.selfie = selfie;
	}

	// Getters
	public String getSubscriberUid() {
		return subscriberUid;
	}

	public String getFullName() {
		return fullName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public String getSelfieUri() {
		return selfieUri;
	}

	public String getGender() {
		return gender;
	}

	public String getOnboardingDataFieldsJson() {
		return onboardingDataFieldsJson;
	}

	public String getSelfie() {
		return selfie;
	}
}
