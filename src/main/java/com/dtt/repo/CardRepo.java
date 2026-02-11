package com.dtt.repo;


import com.dtt.model.Card;
import com.dtt.requestDTO.SubscriberDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


@Repository
public interface CardRepo extends JpaRepository<Card,Integer> {


//    Card findByIdDocNumber(String idDocNumber);
//    @Query(value = "CALL ra_0_2.get_subscriber_details_by_doc_number(?1)")
//    SubscriberDetailsDto getSubscriberDetailsByDocumentNumber(String idDocNumber);


//    @Query("SELECT new com.dtt.idcard.dto.SubscriberDetailsDto( " +
//            "s.subscriberUid, s.fullName, s.dateOfBirth, s.mobileNumber, " +
//            "s.emailId, s.selfieUri, s.gender, s.onboardingDataFieldsJson, s.createdOn) " +
//            "FROM SubscriberDetails s " +
//            "WHERE s.subscriberUid = (" +
//            "   SELECT c.subscriberUid FROM Card c WHERE c.idDocNumber = :idDocNumber" +
//            ")")
//    SubscriberDetailsDto getSubscriberDetailsByDocumentNumber(@Param("idDocNumber") String idDocNumber);


	Card findByIdDocNumber(String idDocNumber);

	@Query("""
    SELECT new com.dtt.requestDTO.SubscriberDetailsDto(
        s.subscriberUid,
        s.fullName,
        s.dateOfBirth,
        s.mobileNumber,
        s.emailId,
        s.createdDate,
        sd.selfieUri,
        sd.gender,
        sd.onboardingDataFieldsJson,
        sd.selfie
    )
    FROM Subscriber s
    JOIN SubscriberOnboardingData sd
        ON sd.subscriberUid = s.subscriberUid
    WHERE s.idDocNumber = :idDocNumber
      AND sd.createdDate = (
          SELECT MAX(sd2.createdDate)
          FROM SubscriberOnboardingData sd2
          WHERE sd2.subscriberUid = s.subscriberUid
      )
""")
	SubscriberDetailsDto getSubscriberDetailsByDocumentNumber(@Param("idDocNumber") String idDocNumber);


//    @Procedure(name = "Card.getSubscriberDetailsByDocNumber")
//    SubscriberDetailsDto getSubscriberDetailsByDocumentNumber(@Param("idDocNumber") String idDocNumber);



}



