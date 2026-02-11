package com.dtt.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dtt.requestDTO.NotificationContextDTO;

@Service
public class AndroidPushNotificationsService {
	public AndroidPushNotificationsService() {
	}

	public Map createMap(NotificationContextDTO notificationContextDTO) {
		System.out.println("notificationContextDTO ==> " + notificationContextDTO);
		Map<String, Object> map = new HashMap();
		if (notificationContextDTO.ispREF_ONBOARDING_STATUS()) {
			map.put("PREF_ONBOARDING_STATUS", true);
		}

		if (notificationContextDTO.getpREF_ONBOARDING_APPROVAL_STATUS() != null) {
			map.put("PREF_ONBOARDING_APPROVAL_STATUS", notificationContextDTO.getpREF_ONBOARDING_APPROVAL_STATUS());
		}

		if (notificationContextDTO.getpREF_CERTIFICATE_STATUS() != null) {
			map.put("PREF_CERTIFICATE_STATUS", notificationContextDTO.getpREF_CERTIFICATE_STATUS());
		}

		if (notificationContextDTO.ispREF_CERTIFICATE_REVOKE_STATUS()) {
			map.put("PREF_CERTIFICATE_REVOKE_STATUS", true);
		}

		if (notificationContextDTO.getpROMOTIONAL_NOTIFICATION() != null) {
			map.put("PROMOTIONAL_NOTIFICATION", notificationContextDTO.getpROMOTIONAL_NOTIFICATION());
		}

		if (notificationContextDTO.issIGNING_AUTH()) {
			map.put("SIGNING_AUTH", notificationContextDTO.issIGNING_AUTH());
			map.put("IS_SIGN_HASH", notificationContextDTO.isiSSIGN_HASH());
			map.put("IS_BOTH_PINS", notificationContextDTO.isiS_BOTH_PINS());
			map.put("IS_SIGN_PINS", notificationContextDTO.isiS_SIGN_PINS());
		}

		if (notificationContextDTO.getpREF_DOCUMENT_SIGNED() != null) {
			map.put("PREF_DOCUMENT_SIGNED",
					(new JSONObject(notificationContextDTO.getpREF_DOCUMENT_SIGNED())).toString());
		}

		if (notificationContextDTO.getpREF_PAYMENT_STATUS() != null
				|| notificationContextDTO.getpREF_TRANSACTION_ID() != null) {
			map.put("PREF_PAYMENT_STATUS",
					(new JSONObject(notificationContextDTO.getpREF_PAYMENT_STATUS())).toString());
			map.put("PREF_TRANSACTION_ID",
					(new JSONObject(notificationContextDTO.getpREF_TRANSACTION_ID())).toString());
		}

		if (notificationContextDTO.getpREF_ORG_LINK() != null) {
			map.put("PREF_ORG_LINK", (new JSONObject(notificationContextDTO.getpREF_ORG_LINK())).toString());
		}

		if (notificationContextDTO.getpREF_BENEFICIARY_LINK() != null) {
			map.put("PREF_BENEFICIARY_LINK",
					(new JSONObject(notificationContextDTO.getpREF_BENEFICIARY_LINK())).toString());
		}

		if (notificationContextDTO.getpREF_ODOO_NOTIFICATION() != null) {
			map.put("PREF_ODOO_NOTIFICATION",
					(new JSONObject(notificationContextDTO.getpREF_ODOO_NOTIFICATION())).toString());
		}

		if (notificationContextDTO.getpREF_VISA_AUTHORITY() != null) {
			map.put("PREF_VISA_AUTHORITY",
					(new JSONObject(notificationContextDTO.getpREF_VISA_AUTHORITY())).toString());
		}

		if (notificationContextDTO.getpREF_IMMIGRATION_AUTHORITY() != null) {
			map.put("PREF_IMMIGRATION_AUTHORITY",
					(new JSONObject(notificationContextDTO.getpREF_IMMIGRATION_AUTHORITY())).toString());
		}

		return map;
	}
}
