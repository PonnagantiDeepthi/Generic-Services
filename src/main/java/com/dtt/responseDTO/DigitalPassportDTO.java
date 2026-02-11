package com.dtt.responseDTO;

public class DigitalPassportDTO {

    private String documentType;
    private String documentNo;

    private String documentNationalityAbbr;
    private String documentNationality;

    private String passportIssueDate;
    private String passportExpiryDate;

    private String firstNameEn;
    private String secondNameEn;

    private String dateOfBirth;
    private String genderEn;

    private String placeOfBirthEn;
    private String passportIssuePlace;

    private String digitalSignature;
    private String personFace;

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getDocumentNationalityAbbr() {
        return documentNationalityAbbr;
    }

    public void setDocumentNationalityAbbr(String documentNationalityAbbr) {
        this.documentNationalityAbbr = documentNationalityAbbr;
    }

    public String getDocumentNationality() {
        return documentNationality;
    }

    public void setDocumentNationality(String documentNationality) {
        this.documentNationality = documentNationality;
    }

    public String getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(String passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getPassportExpiryDate() {
        return passportExpiryDate;
    }

    public void setPassportExpiryDate(String passportExpiryDate) {
        this.passportExpiryDate = passportExpiryDate;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getSecondNameEn() {
        return secondNameEn;
    }

    public void setSecondNameEn(String secondNameEn) {
        this.secondNameEn = secondNameEn;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGenderEn() {
        return genderEn;
    }

    public void setGenderEn(String genderEn) {
        this.genderEn = genderEn;
    }

    public String getPlaceOfBirthEn() {
        return placeOfBirthEn;
    }

    public void setPlaceOfBirthEn(String placeOfBirthEn) {
        this.placeOfBirthEn = placeOfBirthEn;
    }

    public String getPassportIssuePlace() {
        return passportIssuePlace;
    }

    public void setPassportIssuePlace(String passportIssuePlace) {
        this.passportIssuePlace = passportIssuePlace;
    }

    public String getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(String digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

    public String getPersonFace() {
        return personFace;
    }

    public void setPersonFace(String personFace) {
        this.personFace = personFace;
    }

    @Override
    public String toString() {
        return "DigitalPassportDTO{" +
                "documentType='" + documentType + '\'' +
                ", documentNo='" + documentNo + '\'' +
                ", documentNationalityAbbr='" + documentNationalityAbbr + '\'' +
                ", documentNationality='" + documentNationality + '\'' +
                ", passportIssueDate='" + passportIssueDate + '\'' +
                ", passportExpiryDate='" + passportExpiryDate + '\'' +
                ", firstNameEn='" + firstNameEn + '\'' +
                ", secondNameEn='" + secondNameEn + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", genderEn='" + genderEn + '\'' +
                ", placeOfBirthEn='" + placeOfBirthEn + '\'' +
                ", passportIssuePlace='" + passportIssuePlace + '\'' +
                ", digitalSignature='" + digitalSignature + '\'' +
                ", personFace='" + personFace + '\'' +
                '}';
    }
}
