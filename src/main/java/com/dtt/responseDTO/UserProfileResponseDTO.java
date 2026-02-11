package com.dtt.responseDTO;

public class UserProfileResponseDTO {

    private String suid;
    private String birthdate;
    private String name;
    private String phone;
    private String email;
    private String gender;
    private String id_document_type;
    private String id_document_number;
    private String loa;
    private String country;

    private String fullNameEn;
    private String dateOfBirth;
    private String currentNationality;
    private String genderEn;
    private String occupationEn;
    private String emiratesIdNumber;
    private String issueDate;
    private String expiryDate;
    private String passportType;
    private String passportNo;
    private String documentNationalityAbbr;
    private String documentNationality;
    private String passportIssueDate;
    private String passportExpiryDate;

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId_document_type() {
        return id_document_type;
    }

    public void setId_document_type(String id_document_type) {
        this.id_document_type = id_document_type;
    }

    public String getId_document_number() {
        return id_document_number;
    }

    public void setId_document_number(String id_document_number) {
        this.id_document_number = id_document_number;
    }

    public String getLoa() {
        return loa;
    }

    public void setLoa(String loa) {
        this.loa = loa;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
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

    @Override
    public String toString() {
        return "UserProfileResponseDTO{" +
                "suid='" + suid + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", id_document_type='" + id_document_type + '\'' +
                ", id_document_number='" + id_document_number + '\'' +
                ", loa='" + loa + '\'' +
                ", country='" + country + '\'' +
                ", fullNameEn='" + fullNameEn + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", currentNationality='" + currentNationality + '\'' +
                ", genderEn='" + genderEn + '\'' +
                ", occupationEn='" + occupationEn + '\'' +
                ", emiratesIdNumber='" + emiratesIdNumber + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", passportType='" + passportType + '\'' +
                ", passportNo='" + passportNo + '\'' +
                ", documentNationalityAbbr='" + documentNationalityAbbr + '\'' +
                ", documentNationality='" + documentNationality + '\'' +
                ", passportIssueDate='" + passportIssueDate + '\'' +
                ", passportExpiryDate='" + passportExpiryDate + '\'' +
                '}';
    }
}
