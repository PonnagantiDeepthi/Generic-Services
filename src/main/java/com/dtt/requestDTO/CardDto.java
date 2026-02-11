package com.dtt.requestDTO;

public class CardDto {

	private String name;
	private String dateOfBirth;
	private String cardNumber;
	private String pidIssueDate;
	private String pidExpiryDate;
	private String nationality;
	private String photo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPidIssueDate() {
		return pidIssueDate;
	}

	public void setPidIssueDate(String pidIssueDate) {
		this.pidIssueDate = pidIssueDate;
	}

	public String getPidExpiryDate() {
		return pidExpiryDate;
	}

	public void setPidExpiryDate(String pidExpiryDate) {
		this.pidExpiryDate = pidExpiryDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "PidDto{" + "name='" + name + '\'' + ", dateOfBirth='" + dateOfBirth + '\'' + ", cardNumber='"
				+ cardNumber + '\'' + ", pidIssueDate='" + pidIssueDate + '\'' + ", pidExpiryDate='" + pidExpiryDate
				+ '\'' + ", nationality='" + nationality + '\'' + ", photo='" + photo + '\'' + '}';
	}
}
