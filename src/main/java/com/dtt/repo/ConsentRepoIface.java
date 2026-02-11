package com.dtt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtt.model.Consent;



@Repository
public interface ConsentRepoIface extends JpaRepository<Consent, Integer> {

	@Query("SELECT c FROM Consent c WHERE c.consentType = :consentType")
	Consent getConsentByConsentType(@Param("consentType") String consentType);

}