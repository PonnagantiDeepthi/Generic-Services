package com.dtt.responseDTO;

public class UAEIDAuthenticationDTO {

    private String fullNameEn;
    private String dateOfBirth;
    private String currentNationality;
    private String genderEn;
    private String occupationEn;

    private String emiratesIdNumber;
    private String issueDate;
    private String expiryDate;

    private String documentType;
    private String documentNo;
    private String documentNationalityAbbr;
    private String documentNationality;

    private String passportType;
    private String passportIssueDate;
    private String passportExpiryDate;


    private String suid;
    private String loa;


    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCurrentNationality() {
        return currentNationality;
    }

    public void setCurrentNationality(String currentNationality) {
        this.currentNationality = currentNationality;
    }

    public String getGenderEn() {
        return genderEn;
    }

    public void setGenderEn(String genderEn) {
        this.genderEn = genderEn;
    }

    public String getOccupationEn() {
        return occupationEn;
    }

    public void setOccupationEn(String occupationEn) {
        this.occupationEn = occupationEn;
    }

    public String getEmiratesIdNumber() {
        return emiratesIdNumber;
    }

    public void setEmiratesIdNumber(String emiratesIdNumber) {
        this.emiratesIdNumber = emiratesIdNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

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

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getLoa() {
        return loa;
    }

    public void setLoa(String loa) {
        this.loa = loa;
    }

    @Override
    public String toString() {
        return "UAEIDAuthenticationDTO{" +
                "fullNameEn='" + fullNameEn + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", currentNationality='" + currentNationality + '\'' +
                ", genderEn='" + genderEn + '\'' +
                ", occupationEn='" + occupationEn + '\'' +
                ", emiratesIdNumber='" + emiratesIdNumber + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentNo='" + documentNo + '\'' +
                ", documentNationalityAbbr='" + documentNationalityAbbr + '\'' +
                ", documentNationality='" + documentNationality + '\'' +
                ", passportType='" + passportType + '\'' +
                ", passportIssueDate='" + passportIssueDate + '\'' +
                ", passportExpiryDate='" + passportExpiryDate + '\'' +
                ", suid='" + suid + '\'' +
                ", load='" + loa + '\'' +
                '}';
    }
}
