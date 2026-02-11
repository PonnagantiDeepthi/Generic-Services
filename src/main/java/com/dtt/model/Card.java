package com.dtt.model;

import jakarta.persistence.*;

import java.util.Date;

import com.dtt.requestDTO.SubscriberDetailsDto;

@Entity
@Table(name = "subscriber_ugpass_id_card")

@NamedStoredProcedureQuery(name = "Card.getSubscriberDetailsByDocNumber", procedureName = "ra_0_2.get_subscriber_details_by_doc_number", resultSetMappings = "SubscriberDetailsDtoMapping", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "idDocNumber", type = String.class) })
@SqlResultSetMapping(name = "SubscriberDetailsDtoMapping", classes = @ConstructorResult(targetClass = SubscriberDetailsDto.class, columns = {
		@ColumnResult(name = "subscriber_uid", type = String.class),
		@ColumnResult(name = "full_name", type = String.class),
		@ColumnResult(name = "email_id", type = String.class) }))
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "mob_no")
	private String mobileNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "photo")
	private String photo;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "updated_on")
	private Date updatedOn;

	@Column(name = "id_doc_number")
	private String idDocNumber;

	@Column(name = "pid_issue_date")
	private String pidIssueDate;

	@Column(name = "pid_expiry_date")
	private String pidExpiryDate;

	@Column(name = "pid_document")
	private String pidDocument;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "subscriber_uid")
	private String subscriberUid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getIdDocNumber() {
		return idDocNumber;
	}

	public void setIdDocNumber(String idDocNumber) {
		this.idDocNumber = idDocNumber;
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

	public String getPidDocument() {
		return pidDocument;
	}

	public void setPidDocument(String pidDocument) {
		this.pidDocument = pidDocument;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getSubscriberUid() {
		return subscriberUid;
	}

	public void setSubscriberUid(String subscriberUid) {
		this.subscriberUid = subscriberUid;
	}

	@Override
	public String toString() {
		return "Card{" + "id=" + id + ", fullName='" + fullName + '\'' + ", dateOfBirth='" + dateOfBirth + '\''
				+ ", gender='" + gender + '\'' + ", nationality='" + nationality + '\'' + ", countryCode='"
				+ countryCode + '\'' + ", mobileNumber='" + mobileNumber + '\'' + ", email='" + email + '\''
				+ ", address='" + address + '\'' + ", photo='" + photo + '\'' + ", createdOn=" + createdOn
				+ ", updatedOn=" + updatedOn + ", idDocNumber='" + idDocNumber + '\'' + ", pidIssueDate='"
				+ pidIssueDate + '\'' + ", pidExpiryDate='" + pidExpiryDate + '\'' + ", pidDocument='" + pidDocument
				+ '\'' + ", cardNumber='" + cardNumber + '\'' + ", subscriberUid='" + subscriberUid + '\'' + '}';
	}
}
