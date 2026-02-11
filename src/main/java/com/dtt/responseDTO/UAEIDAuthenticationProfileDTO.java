package com.dtt.responseDTO;

public class UAEIDAuthenticationProfileDTO {

    private String fullName;
    private String nationality;

    private String emiratesIdNumber;
    private String emiratesIdExpiryDate;

    private String passportNumber;
    private String passportExpiryDate;

    private String suid;
    private String loa;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmiratesIdNumber() {
        return emiratesIdNumber;
    }

    public void setEmiratesIdNumber(String emiratesIdNumber) {
        this.emiratesIdNumber = emiratesIdNumber;
    }

    public String getEmiratesIdExpiryDate() {
        return emiratesIdExpiryDate;
    }

    public void setEmiratesIdExpiryDate(String emiratesIdExpiryDate) {
        this.emiratesIdExpiryDate = emiratesIdExpiryDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportExpiryDate() {
        return passportExpiryDate;
    }

    public void setPassportExpiryDate(String passportExpiryDate) {
        this.passportExpiryDate = passportExpiryDate;
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
        return "UAEIDAuthenticationProfileDTO{" +
                "fullName='" + fullName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", emiratesIdNumber='" + emiratesIdNumber + '\'' +
                ", emiratesIdExpiryDate='" + emiratesIdExpiryDate + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportExpiryDate='" + passportExpiryDate + '\'' +
                ", suid='" + suid + '\'' +
                ", loa='" + loa + '\'' +
                '}';
    }
}
