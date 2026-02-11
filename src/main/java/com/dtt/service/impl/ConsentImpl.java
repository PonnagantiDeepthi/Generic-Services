package com.dtt.service.impl;

import org.hibernate.PessimisticLockException;
import org.hibernate.QueryTimeoutException;
import org.hibernate.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dtt.model.Consent;
import com.dtt.model.ConsentHistory;
import com.dtt.repo.ConsentHistoryRepoIface;
import com.dtt.repo.ConsentRepoIface;
import com.dtt.requestDTO.ApiResponse;
import com.dtt.requestDTO.ConsentDTO;
import com.dtt.service.iface.ConsentIface;
import com.dtt.utils.AppUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ConsentImpl implements ConsentIface {

    @Autowired
    ConsentHistoryRepoIface consentHistoryRepoIface;

    @Autowired
    ConsentRepoIface consentRepoIface;


    @Autowired
    MessageSource messageSource;

    @Value(value = "${directoryPath}")
    private String directoryPath;


    @Override
    public ApiResponse addFiles(ConsentDTO consentDTO, MultipartFile termsAndConditions, MultipartFile dataPrivacy) {

        try {
//            if (consentDTO.getConsent() == null || consentDTO.getConsent().equals("")) {
//                return AppUtil.createApiResponse(false,"Consent cannot be empty", null);
//            }
//
//            Optional<Consent> savedConsent = Optional.ofNullable(consentRepoIface.getConsentByConsentType(consentDTO.getConsentType()));
//
//            if(!savedConsent.isPresent()){
//                return AppUtil.createApiResponse(false,"consent data can't be null", null);
//            }
//
//            //Saving data into consent_history table
//           ConsentHistory consentHistory = new ConsentHistory();
//            consentHistory.setConsentId(savedConsent.get().getConsentId());
//            consentHistory.setConsent(consentDTO.getConsent());
//            consentHistory.setPrivacyConsent(consentDTO.getPrivacyConsent());
//            consentHistory.setCreatedOn(AppUtil.getDate());
//            if(!termsAndConditions.isEmpty()){
//                byte[] needToBeBase62 = termsAndConditions.getBytes();
//                String base64String = AppUtil.getBase64FromByteArr(needToBeBase62);
//                String originalFileName = termsAndConditions.getOriginalFilename();
//                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
//                consentHistory.setOptionalTermsAndConditions(base64String);
//
//                // Define allowed extensions
//                String allowedExtension = ".html";
//
//                if (!fileExtension.equals(allowedExtension)) {
//                    return AppUtil.createApiResponse(false, "Files with extension .html is only accepted", null);
//                }
//            }else{
//                consentHistory.setOptionalTermsAndConditions(null);
//            }
////            System.out.println(dataPrivacy==null);
//            System.out.println(dataPrivacy.isEmpty());
//            if(!dataPrivacy.isEmpty()){
//                byte[] needToBeBase62 = dataPrivacy.getBytes();
//                String base64String = AppUtil.getBase64FromByteArr(needToBeBase62);
//                String originalFileName = dataPrivacy.getOriginalFilename();
//                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
//                consentHistory.setOptionalDataAndPrivacy(base64String);
//
//                // Define allowed extensions
//                String allowedExtensions = ".html";
////                List<String> myList = Arrays.asList(allowedExtensions);
//                if (!allowedExtensions.equals(fileExtension)) {
//                    return AppUtil.createApiResponse(false, "Files with extension .html is only accepted", null);
//                }
//            }else{
//                consentHistory.setOptionalDataAndPrivacy(null);
//            }
//            consentHistory.setConsentType(consentDTO.getConsentType());
//            consentHistory.setConsentRequired(consentDTO.getConsentRequired());
//            ConsentHistory saveInDataBaseConsentHistory = consentHistoryRepoIface.save(consentHistory);
//
//
//            //Saving data into main Consent table
////            Consent savedConsent = new Consent();
//            savedConsent.get().setCreatedOn(AppUtil.getDate());
//            savedConsent.get().setUpdatedOn(AppUtil.getDate());
//            savedConsent.get().setStatus("INACTIVE");
//            savedConsent.get().setConsentType("SUBSCRIBER");
//            savedConsent.get().setConsent(consentDTO.getConsent());
//            savedConsent.get().setPrivacyConsent(consentDTO.getPrivacyConsent());
//
//            Consent savedData = consentRepoIface.save(savedConsent.get());
//
//            Thread thread = new Thread(() -> {
//                // Call the asynchronous method here
//
//
//                try {
//                    storefiles( termsAndConditions, dataPrivacy);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//
//           if(!termsAndConditions.isEmpty() || !dataPrivacy.isEmpty()) {
//               thread.start();
//           }
//
//            if (savedConsent != null) {
//                return AppUtil.createApiResponse(true, "consent saved successfully", null);
//            } else {
//                return AppUtil.createApiResponse(false,"consent not saved", null);
//            }
                        if (consentDTO.getConsent() == null || consentDTO.getConsent().equals("")) {
                return AppUtil.createApiResponse(false,"Consent cannot be empty", null);
            }
            Optional<Consent> savedConsent = Optional.ofNullable(consentRepoIface.getConsentByConsentType(consentDTO.getConsentType()));
            if(!savedConsent.isPresent()){
                Consent consent = new Consent();
                consent.setConsentType(consentDTO.getConsentType());
                consent.setPrivacyConsent(consentDTO.getPrivacyConsent());
                consent.setConsent(consentDTO.getConsent());
                consent.setStatus("INACTIVE");
                consent.setCreatedOn(AppUtil.getDate());
                consent.setUpdatedOn(AppUtil.getDate());
                consentRepoIface.save(consent);
            }
            else{
                savedConsent.get().setConsent(consentDTO.getConsent());
                savedConsent.get().setPrivacyConsent(consentDTO.getPrivacyConsent());
                savedConsent.get().setStatus("INACTIVE");
                savedConsent.get().setUpdatedOn(AppUtil.getDate());
                savedConsent.get().setConsentType(consentDTO.getConsentType());
                consentRepoIface.save(savedConsent.get());
            }
            Optional<Consent> newStoredConsent = Optional.ofNullable(consentRepoIface.getConsentByConsentType(consentDTO.getConsentType()));
            ConsentHistory consentHistory= new ConsentHistory();
            consentHistory.setConsentRequired(consentDTO.getConsentRequired());
            consentHistory.setPrivacyConsent(consentDTO.getPrivacyConsent());
            consentHistory.setConsentType(consentDTO.getConsentType());
//            consentHistory.setOptionalDataAndPrivacy();
            System.out.println(termsAndConditions);
            if(!termsAndConditions.isEmpty()){
                byte[] needToBeBase62 = termsAndConditions.getBytes();
                String base64String = AppUtil.getBase64FromByteArr(needToBeBase62);
                String originalFileName = termsAndConditions.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                consentHistory.setOptionalTermsAndConditions(base64String);

                // Define allowed extensions
                String allowedExtension = ".html";

                if (!fileExtension.equals(allowedExtension)) {
                    return AppUtil.createApiResponse(false, "Files with extension .html is only accepted", null);
                }
            }else{
                consentHistory.setOptionalTermsAndConditions(null);
            }
            if(!dataPrivacy.isEmpty()){
                byte[] needToBeBase62 = dataPrivacy.getBytes();
                String base64String = AppUtil.getBase64FromByteArr(needToBeBase62);
                String originalFileName = dataPrivacy.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                consentHistory.setOptionalDataAndPrivacy(base64String);

                // Define allowed extensions
                String allowedExtensions = ".html";
//                List<String> myList = Arrays.asList(allowedExtensions);
                if (!allowedExtensions.equals(fileExtension)) {
                    return AppUtil.createApiResponse(false, "Files with extension .html is only accepted", null);
                }
            }else{
                consentHistory.setOptionalDataAndPrivacy(null);
            }
            consentHistory.setConsent(consentDTO.getConsent());
            consentHistory.setCreatedOn(AppUtil.getDate());
            consentHistory.setConsentId(newStoredConsent.get().getConsentId());
            consentHistoryRepoIface.save(consentHistory);
            Thread thread = new Thread(() -> {

                try {
                    storefiles( termsAndConditions, dataPrivacy);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

           if(!termsAndConditions.isEmpty() || !dataPrivacy.isEmpty()) {
               thread.start();
           }

            if (savedConsent != null) {
                return AppUtil.createApiResponse(true, "consent saved successfully", null);
			} else {
                return AppUtil.createApiResponse(false,"consent not saved", null);
            }

        }

        catch (JDBCConnectionException | ConstraintViolationException | DataException | LockAcquisitionException | PessimisticLockException
               | QueryTimeoutException | SQLGrammarException | GenericJDBCException e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"something went wrong please try after sometime",  null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"something went wrong please try after sometime", null);
        }


    }


    private ApiResponse storefiles(MultipartFile termsAndConditions,MultipartFile dataPrivacy) throws IOException {

        try {

            if(!termsAndConditions.isEmpty()){
                Path filePath1 = Paths.get(directoryPath + termsAndConditions.getOriginalFilename());
                Files.write(filePath1, termsAndConditions.getBytes());
            }
            if(!dataPrivacy.isEmpty()) {
                Path filePath2 = Paths.get(directoryPath + dataPrivacy.getOriginalFilename());
                Files.write(filePath2, dataPrivacy.getBytes());
            }
            return AppUtil.createApiResponse(true, "File saved successfully", null);
        }catch(Exception e){
            return AppUtil.createApiResponse(false, "File not saved in destination folder", null);
        }
    }





}

