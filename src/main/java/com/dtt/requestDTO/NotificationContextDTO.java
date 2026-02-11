package com.dtt.requestDTO;

import java.io.Serializable;
import java.util.Map;

import com.dtt.enums.CertificateStatus;
import com.dtt.enums.OnboardingApprovalStatus;

public class NotificationContextDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean pREF_ONBOARDING_STATUS;
	private OnboardingApprovalStatus pREF_ONBOARDING_APPROVAL_STATUS;
	private CertificateStatus pREF_CERTIFICATE_STATUS;
	private boolean pREF_CERTIFICATE_REVOKE_STATUS;
	private String pROMOTIONAL_NOTIFICATION;
	private boolean sIGNING_AUTH;
	private boolean iSSIGN_HASH;
	private boolean iS_BOTH_PINS;
	private boolean iS_SIGN_PINS;
	private Map<String, String> pREF_DOCUMENT_SIGNED;
	private Map<String, String> pREF_PAYMENT_STATUS;
	private Map<String, String> pREF_TRANSACTION_ID;
	private Map<String, String> pREF_ORG_LINK;

	private Map<String, String> pREF_BENEFICIARY_LINK;

	private Map<String, String> pREF_ODOO_NOTIFICATION;
	private Map<String, String> pREF_VISA_AUTHORITY;
	private Map<String, String> pREF_IMMIGRATION_AUTHORITY;

	public Map<String, String> getpREF_ODOO_NOTIFICATION() {
		return pREF_ODOO_NOTIFICATION;
	}

	public void setpREF_ODOO_NOTIFICATION(Map<String, String> pREF_ODOO_NOTIFICATION) {
		this.pREF_ODOO_NOTIFICATION = pREF_ODOO_NOTIFICATION;
	}

	public Map<String, String> getpREF_VISA_AUTHORITY() {
		return pREF_VISA_AUTHORITY;
	}

	public void setpREF_VISA_AUTHORITY(Map<String, String> pREF_VISA_AUTHORITY) {
		this.pREF_VISA_AUTHORITY = pREF_VISA_AUTHORITY;
	}

	public Map<String, String> getpREF_IMMIGRATION_AUTHORITY() {
		return pREF_IMMIGRATION_AUTHORITY;
	}

	public void setpREF_IMMIGRATION_AUTHORITY(Map<String, String> pREF_IMMIGRATION_AUTHORITY) {
		this.pREF_IMMIGRATION_AUTHORITY = pREF_IMMIGRATION_AUTHORITY;
	}

	public Map<String, String> getpREF_BENEFICIARY_LINK() {
		return pREF_BENEFICIARY_LINK;
	}

	public void setpREF_BENEFICIARY_LINK(Map<String, String> pREF_BENEFICIARY_LINK) {
		this.pREF_BENEFICIARY_LINK = pREF_BENEFICIARY_LINK;
	}

	public Map<String, String> getpREF_ORG_LINK() {
		return this.pREF_ORG_LINK;
	}

	public void setpREF_ORG_LINK(Map<String, String> pREF_ORG_LINK) {
		this.pREF_ORG_LINK = pREF_ORG_LINK;
	}

	public NotificationContextDTO() {
	}

	public boolean ispREF_ONBOARDING_STATUS() {
		return this.pREF_ONBOARDING_STATUS;
	}

	public void setpREF_ONBOARDING_STATUS(boolean pREF_ONBOARDING_STATUS) {
		this.pREF_ONBOARDING_STATUS = pREF_ONBOARDING_STATUS;
	}

	public CertificateStatus getpREF_CERTIFICATE_STATUS() {
		return this.pREF_CERTIFICATE_STATUS;
	}

	public void setpREF_CERTIFICATE_STATUS(CertificateStatus pREF_CERTIFICATE_STATUS) {
		this.pREF_CERTIFICATE_STATUS = pREF_CERTIFICATE_STATUS;
	}

	public OnboardingApprovalStatus getpREF_ONBOARDING_APPROVAL_STATUS() {
		return this.pREF_ONBOARDING_APPROVAL_STATUS;
	}

	public void setpREF_ONBOARDING_APPROVAL_STATUS(OnboardingApprovalStatus pREF_ONBOARDING_APPROVAL_STATUS) {
		this.pREF_ONBOARDING_APPROVAL_STATUS = pREF_ONBOARDING_APPROVAL_STATUS;
	}

	public boolean ispREF_CERTIFICATE_REVOKE_STATUS() {
		return this.pREF_CERTIFICATE_REVOKE_STATUS;
	}

	public void setpREF_CERTIFICATE_REVOKE_STATUS(boolean pREF_CERTIFICATE_REVOKE_STATUS) {
		this.pREF_CERTIFICATE_REVOKE_STATUS = pREF_CERTIFICATE_REVOKE_STATUS;
	}

	public String getpROMOTIONAL_NOTIFICATION() {
		return this.pROMOTIONAL_NOTIFICATION;
	}

	public void setpROMOTIONAL_NOTIFICATION(String pROMOTIONAL_NOTIFICATION) {
		this.pROMOTIONAL_NOTIFICATION = pROMOTIONAL_NOTIFICATION;
	}

	public boolean issIGNING_AUTH() {
		return this.sIGNING_AUTH;
	}

	public void setsIGNING_AUTH(boolean sIGNING_AUTH) {
		this.sIGNING_AUTH = sIGNING_AUTH;
	}

	public boolean isiSSIGN_HASH() {
		return this.iSSIGN_HASH;
	}

	public void setiSSIGN_HASH(boolean iSSIGN_HASH) {
		this.iSSIGN_HASH = iSSIGN_HASH;
	}

	public Map<String, String> getpREF_DOCUMENT_SIGNED() {
		return this.pREF_DOCUMENT_SIGNED;
	}

	public void setpREF_DOCUMENT_SIGNED(Map<String, String> pREF_DOCUMENT_SIGNED) {
		this.pREF_DOCUMENT_SIGNED = pREF_DOCUMENT_SIGNED;
	}

	public Map<String, String> getpREF_PAYMENT_STATUS() {
		return this.pREF_PAYMENT_STATUS;
	}

	public void setpREF_PAYMENT_STATUS(Map<String, String> pREF_PAYMENT_STATUS) {
		this.pREF_PAYMENT_STATUS = pREF_PAYMENT_STATUS;
	}

	public Map<String, String> getpREF_TRANSACTION_ID() {
		return this.pREF_TRANSACTION_ID;
	}

	public void setpREF_TRANSACTION_ID(Map<String, String> pREF_TRANSACTION_ID) {
		this.pREF_TRANSACTION_ID = pREF_TRANSACTION_ID;
	}

	public boolean isiS_BOTH_PINS() {
		return this.iS_BOTH_PINS;
	}

	public void setiS_BOTH_PINS(boolean iS_BOTH_PINS) {
		this.iS_BOTH_PINS = iS_BOTH_PINS;
	}

	public boolean isiS_SIGN_PINS() {
		return this.iS_SIGN_PINS;
	}

	public void setiS_SIGN_PINS(boolean iS_SIGN_PINS) {
		this.iS_SIGN_PINS = iS_SIGN_PINS;
	}

	@Override
	public String toString() {
		return "NotificationContextDTO [pREF_ONBOARDING_STATUS=" + pREF_ONBOARDING_STATUS
				+ ", pREF_ONBOARDING_APPROVAL_STATUS=" + pREF_ONBOARDING_APPROVAL_STATUS + ", pREF_CERTIFICATE_STATUS="
				+ pREF_CERTIFICATE_STATUS + ", pREF_CERTIFICATE_REVOKE_STATUS=" + pREF_CERTIFICATE_REVOKE_STATUS
				+ ", pROMOTIONAL_NOTIFICATION=" + pROMOTIONAL_NOTIFICATION + ", sIGNING_AUTH=" + sIGNING_AUTH
				+ ", iSSIGN_HASH=" + iSSIGN_HASH + ", iS_BOTH_PINS=" + iS_BOTH_PINS + ", iS_SIGN_PINS=" + iS_SIGN_PINS
				+ ", pREF_DOCUMENT_SIGNED=" + pREF_DOCUMENT_SIGNED + ", pREF_PAYMENT_STATUS=" + pREF_PAYMENT_STATUS
				+ ", pREF_TRANSACTION_ID=" + pREF_TRANSACTION_ID + ", pREF_ORG_LINK=" + pREF_ORG_LINK
				+ ", pREF_BENEFICIARY_LINK=" + pREF_BENEFICIARY_LINK + ", pREF_ODOO_NOTIFICATION="
				+ pREF_ODOO_NOTIFICATION + ", pREF_VISA_AUTHORITY=" + pREF_VISA_AUTHORITY
				+ ", pREF_IMMIGRATION_AUTHORITY=" + pREF_IMMIGRATION_AUTHORITY + "]";
	}

// public String toString() {
//     return "NotificationContextDTO{pREF_ONBOARDING_STATUS=" + this.pREF_ONBOARDING_STATUS + ", pREF_ONBOARDING_APPROVAL_STATUS=" + this.pREF_ONBOARDING_APPROVAL_STATUS + ", pREF_CERTIFICATE_STATUS=" + this.pREF_CERTIFICATE_STATUS + ", pREF_CERTIFICATE_REVOKE_STATUS=" + this.pREF_CERTIFICATE_REVOKE_STATUS + ", pROMOTIONAL_NOTIFICATION='" + this.pROMOTIONAL_NOTIFICATION + '\'' + ", sIGNING_AUTH=" + this.sIGNING_AUTH + ", iSSIGN_HASH=" + this.iSSIGN_HASH + ", iS_BOTH_PINS=" + this.iS_BOTH_PINS + ", iS_SIGN_PINS=" + this.iS_SIGN_PINS + ", pREF_DOCUMENT_SIGNED=" + this.pREF_DOCUMENT_SIGNED + ", pREF_PAYMENT_STATUS=" + this.pREF_PAYMENT_STATUS + ", pREF_TRANSACTION_ID=" + this.pREF_TRANSACTION_ID + ", pREF_ORG_LINK=" + this.pREF_ORG_LINK + ", pREF_BENEFICIARY_LINK=" + this.pREF_BENEFICIARY_LINK + '}';
// }
}
