package com.dtt.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dtt.model.Subscriber;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface SubscriberRepoIface extends JpaRepository<Subscriber, String> {

	// ===== Subscriber counts =====
//	@Query("SELECT COUNT(s) FROM Subscriber s WHERE s.mobileNumber = ?1")
//	int countSubscriberMobile(String mobileNo);
//
//	@Query("SELECT COUNT(s) FROM Subscriber s WHERE s.emailId = ?1")
//	int countSubscriberEmailId(String emailId);
//
//	// This uses JPQL only if you have a SubscriberDevice entity, otherwise keep native
//	@Query("SELECT COUNT(d.deviceUid) FROM SubscriberDevice d WHERE d.deviceUid = ?1")
//	int countSubscriberDevice(String deviceId);
//
//	// ===== Subscriber fetches =====
//	@Query("SELECT s FROM Subscriber s WHERE s.mobileNumber = ?1 AND s.emailId = ?2")
//	Subscriber findFCMTokenByMobileEamil(String mobileNo, String email);

//	Subscriber findByemailId(String emailId);
//	Subscriber findBysubscriberUid(String suid);
//	Subscriber findBymobileNumber(String mobileNo);

//	@Query("SELECT s FROM Subscriber s WHERE s.emailId = ?1 OR s.mobileNumber = ?2")
//	Subscriber getSubscriberUidByEmailAndMobile(String email, String mobile);
//
//	@Query("SELECT s FROM Subscriber s WHERE s.emailId = ?1 AND s.mobileNumber = ?2")
//	Subscriber getSubscriberDetailsByEmailAndMobile(String email, String mobile);

	@Query("SELECT s FROM Subscriber s WHERE s.idDocNumber = ?1")
	Subscriber findbyDocumentNumber(String idDocument);

//	@Query("SELECT s.emailId FROM Subscriber s WHERE s.emailId LIKE %?1%")
//	List<String> getSubscriberListByEmailId(String emailId);
//
//	@Query("SELECT s.mobileNumber FROM Subscriber s WHERE s.mobileNumber LIKE %?1%")
//	List<String> getSubscriberListByMobileNo(String mobileNo);
//
//	// ===== SubscriberCompleteDetails =====
//	@Query("SELECT scd.videoUrl FROM SubscriberCompleteDetails scd WHERE scd.subscriberUid = ?1")
//	String getSubscriberUid(String subscriberUid);
//
//	// ===== SubscriberOnboardingData =====
//	@Query("SELECT COUNT(sod.idDocNumber) FROM SubscriberOnboardingData sod WHERE sod.idDocNumber = ?1 AND sod.subscriberUid = ?2")
//	int getSubscriberIdDocNumber(String idDocNumber, String subscriberUid);
//
//	@Query("SELECT COUNT(sod.idDocNumber) FROM SubscriberOnboardingData sod WHERE sod.idDocNumber = ?1")
//	int getIdDocCount(String idDocNumber);
//
//	// ===== Subscriber Status =====
//	@Query("SELECT ss.subscriberStatus FROM SubscriberStatus ss WHERE ss.subscriberUid = ?1")
//	String getSubscriberStatus(String subscriberUid);
//
//	@Query("SELECT sclc.certificateStatus FROM SubscriberCertificateLifeCycle sclc WHERE sclc.subscriberUid = ?1 AND sclc.certificateType = 'SIGN' ORDER BY sclc.createdDate DESC")
//	List<String> getCertStatusList(String subscriberUid); // JPQL cannot do LIMIT, fetch first in service
//
//	default String getCertStatus(String subscriberUid) {
//		List<String> list = getCertStatusList(subscriberUid);
//		return list.isEmpty() ? null : list.get(0);
//	}
//
//	@Query("SELECT COUNT(s) FROM Subscriber s")
//	int countOnboarding();
//
//	// ===== SubscriberPaymentHistory =====
//	@Query("SELECT sph.paymentStatus FROM SubscriberPaymentHistory sph " +
//			"WHERE sph.subscriberSuid = ?1 AND sph.paymentCategory IN ('ONE_TIME_AND_CERT_FEE_COLLECTION','USER_SUBSCRIBTION_FEE') " +
//			"AND sph.paymentStatus IN ('Success','Failed','Initiated') ORDER BY sph.paymentStatus DESC")
//	List<String> subscriberPaymnetStatusList(String suid);
//
//	default String subscriberPaymnetStatus(String suid) {
//		List<String> list = subscriberPaymnetStatusList(suid);
//		return list.isEmpty() ? null : list.get(0);
//	}
//
//	@Query("SELECT sph.paymentStatus FROM SubscriberPaymentHistory sph " +
//			"WHERE sph.subscriberSuid = ?1 AND sph.paymentCategory = 'ONE_TIME_AND_CERT_FEE_COLLECTION' " +
//			"AND sph.paymentStatus IN ('Success','Failed','Initiated') ORDER BY sph.createdOn DESC")
//	List<String> subscriberPaymnetStatusOLDList(String suid);
//
//	default String subscriberPaymnetStatusOLD(String suid) {
//		List<String> list = subscriberPaymnetStatusOLDList(suid);
//		return list.isEmpty() ? null : list.get(0);
//	}
//
//	@Query("SELECT DISTINCT sph.paymentStatus FROM SubscriberPaymentHistory sph " +
//			"WHERE sph.subscriberSuid = ?1 AND sph.paymentCategory IN ('CERT_FEE_COLLECTION','USER_SUBSCRIBTION_FEE') " +
//			"AND sph.paymentStatus IN ('Success','Failed','Initiated') ORDER BY sph.paymentStatus DESC")
//	List<String> subscriberPaymnetCertStatusList(String suid);
//
//	default String subscriberPaymnetCertStatus(String suid) {
//		List<String> list = subscriberPaymnetCertStatusList(suid);
//		return list.isEmpty() ? null : list.get(0);
//	}
//
//	@Query("SELECT DISTINCT sph.paymentStatus FROM SubscriberPaymentHistory sph " +
//			"WHERE sph.subscriberSuid = ?1 AND sph.paymentCategory = 'CERT_FEE_COLLECTION' " +
//			"AND sph.paymentStatus IN ('Success','Failed','Initiated') ORDER BY sph.createdOn DESC")
//	List<String> subscriberPaymnetCertStatusOLDList(String suid);
//
//	default String subscriberPaymnetCertStatusOLD(String suid) {
//		List<String> list = subscriberPaymnetCertStatusOLDList(suid);
//		return list.isEmpty() ? null : list.get(0);
//	}
//
//	@Query("SELECT sph.paymentStatus FROM SubscriberPaymentHistory sph " +
//			"WHERE sph.subscriberSuid = ?1 AND sph.paymentCategory IN ('ONE_TIME_AND_CERT_FEE_COLLECTION','USER_SUBSCRIBTION_FEE') " +
//			"AND sph.paymentStatus = 'Initiated' ORDER BY sph.paymentStatus DESC")
//	List<String> subscriberPaymnetInitaiatedStatusList(String suid);
//
//	default String subscriberPaymnetInitaiatedStatus(String suid) {
//		List<String> list = subscriberPaymnetInitaiatedStatusList(suid);
//		return list.isEmpty() ? null : list.get(0);
//	}
//
//	@Query("SELECT sph.paymentStatus FROM SubscriberPaymentHistory sph " +
//			"WHERE sph.subscriberSuid = ?1 AND sph.paymentCategory = 'ONE_TIME_AND_CERT_FEE_COLLECTION' " +
//			"AND sph.paymentStatus = 'Initiated' ORDER BY sph.createdOn DESC")
//	List<String> subscriberPaymnetInitaiatedStatusOLDList(String suid);
//
//	default String subscriberPaymnetInitaiatedStatusOLD(String suid) {
//		List<String> list = subscriberPaymnetInitaiatedStatusOLDList(suid);
//		return list.isEmpty() ? null : list.get(0);
//	}
//
//	@Modifying
//	@Query("DELETE FROM Subscriber s WHERE s.subscriberUid = ?1")
//	int deleteRecordBySubscriberUid(String suid);
//
//	@Query("SELECT sph.paymentStatus FROM SubscriberPaymentHistory sph " +
//			"WHERE sph.subscriberSuid = ?1 AND sph.paymentCategory IN ('ONE_TIME_AND_CERT_FEE_COLLECTION','USER_SUBSCRIBTION_FEE') " +
//			"AND sph.paymentStatus = 'Success' ORDER BY sph.paymentStatus DESC")
//	List<String> firstTimeOnboardingPaymentStatusList(String suid);
//
//	default String firstTimeOnboardingPaymentStatus(String suid) {
//		List<String> list = firstTimeOnboardingPaymentStatusList(suid);
//		return list.isEmpty() ? null : list.get(0);
//	}
//
//	@Query("SELECT sph.paymentStatus FROM SubscriberPaymentHistory sph " +
//			"WHERE sph.subscriberSuid = ?1 AND sph.paymentCategory = 'ONE_TIME_AND_CERT_FEE_COLLECTION' " +
//			"AND sph.paymentStatus = 'Success' ORDER BY sph.createdOn DESC")
//	List<String> firstTimeOnboardingPaymentStatusOLDList(String suid);
//
//	default String firstTimeOnboardingPaymentStatusOLD(String suid) {
//		List<String> list = firstTimeOnboardingPaymentStatusOLDList(suid);
//		return list.isEmpty() ? null : list.get(0);

//	}



Subscriber findBySubscriberUid(String suid);
Subscriber findByPassportNumber(String passportNumber);
Subscriber findByNationalIdNumber(String nationalIdNumber);



}

