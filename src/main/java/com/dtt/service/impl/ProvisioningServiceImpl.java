package com.dtt.service.impl;


import com.dtt.enums.IdentifierType;
import com.dtt.enums.ResponseType;
import com.dtt.model.Subscriber;
import com.dtt.model.SubscriberOnboardingData;
import com.dtt.repo.SubscriberOnboardingDataRepoIface;
import com.dtt.repo.SubscriberRepoIface;
import com.dtt.requestDTO.ApiResponse;
import com.dtt.responseDTO.*;
import com.dtt.service.iface.ProvisioningServiceIface;
import com.dtt.utils.AppUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Optional;

@Service
public class ProvisioningServiceImpl implements ProvisioningServiceIface {


    @Autowired
    SubscriberRepoIface subscriberRepoIface;

    @Autowired
    SubscriberOnboardingDataRepoIface subscriberOnboardingDataRepoIface;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProvisioningServiceImpl.class);


    @Override
    public ApiResponse getProfileDetailsForProvisioning(IdentifierType identifierType, ResponseType responseType,String identifierValue) {

        try {
            logger.info("Fetching profile | identifierType={} | identifierValue={} | responseType={}", identifierType, identifierValue,responseType);
            Optional<SubscriberOnboardingData> profileOpt = fetchProfile(identifierValue, identifierType);


            if (profileOpt.isEmpty()) {
                return AppUtil.createApiResponse(false, "Profile Not Found", null);
            }



            SubscriberOnboardingData profile = profileOpt.get();

            System.out.println("Subscriber Doc Reposne" + profile.getNiraResponse());

            logger.info("Subscriber Document Response:::"+ profile.getNiraResponse());



//           if(!verifyHmac(profile.getNiraResponse(),profile.getDocumentResponseHash())){
//               return AppUtil.createApiResponse(false,"Data is tempered",null);
//            }



            Object responseDto = buildResponse(profile, responseType, identifierValue);
            return AppUtil.createApiResponse(true, "Fetched", responseDto);
        }catch (Exception e){
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"Something Went wrong",null);
        }
    }




    private Optional<SubscriberOnboardingData> fetchProfile(
            String identifierValue,
            IdentifierType identifierType) {

        try {

            return switch (identifierType) {

                case passportNumber -> {
//                    Subscriber subscriber = subscriberRepoIface.findbyDocumentNumber(identifierValue);
                    Subscriber subscriber = subscriberRepoIface.findByPassportNumber(identifierValue);

                    if (subscriber == null || subscriber.getSubscriberUid() == null) {
                        logger.info("No subscriber found for passportNumber={}", identifierValue);
                        yield Optional.empty();
                    }

                    yield subscriberOnboardingDataRepoIface
                            .findTopBySubscriberUidOrderBySubscriberOnboardingDataIdDesc(
                                    subscriber.getSubscriberUid()
                            );
                }

                case IDNumber -> {
//                    Subscriber subscriber =
//                            subscriberRepoIface.findByNationalId(identifierValue);
                    Subscriber subscriber = subscriberRepoIface.findByNationalIdNumber(identifierValue);

                    if (subscriber == null || subscriber.getSubscriberUid() == null) {
                        logger.info("No subscriber found for Emirates ID={}", identifierValue);
                        yield Optional.empty();
                    }

                    yield subscriberOnboardingDataRepoIface
                            .findTopBySubscriberUidOrderBySubscriberOnboardingDataIdDesc(
                                    subscriber.getSubscriberUid()
                            );
                }

                case SUID -> subscriberOnboardingDataRepoIface
                        .findTopBySubscriberUidOrderBySubscriberOnboardingDataIdDesc(
                                identifierValue
                        );
            };
        } catch (Exception ex) {
            logger.info("Error fetching profile. identifierType={}, identifierValue={}",
                    identifierType, identifierValue, ex);
            return Optional.empty();
        }
    }







    private Object buildResponse(
            SubscriberOnboardingData profile,
            ResponseType responseType,
            String identifierValue) {

        try {
            JsonNode root = objectMapper.readTree(profile.getNiraResponse());

            JsonNode dataNode = root
                    .path("customerDetails")
                    .path("Result")
                    .path("Data");

            JsonNode personalInfo = dataNode.path("PersonalInfo");
            JsonNode passport = dataNode.path("ActivePassport");
            JsonNode residence = dataNode.path("ResidenceInfo");
            JsonNode documents = dataNode.path("Documents");
            JsonNode sponsor = dataNode.path("SponsorDetails");
            JsonNode immigrationFile = dataNode.path("ImmigrationFile");
            JsonNode activeVisas = dataNode.path("ActiveVisa");
            return switch (responseType) {


                case EMIRATES_ID -> {
                    DigitalEmiratesIdDTO dto = new DigitalEmiratesIdDTO();

                    dto.setDateOfBirth(personalInfo.path("DateOfBirth").asText("null"));
                    dto.setCurrentNationality(personalInfo.path("CurrentNationality").asText("null"));
                    dto.setGenderEn(personalInfo.path("GenderEn").asText("null"));

                    dto.setFullNameEn(personalInfo.path("FullNameEn").asText("null"));
                    dto.setFirstNameEn(personalInfo.path("FirstNameEn").asText("null"));
                    dto.setSecondNameEn(personalInfo.path("SecondNameEn").asText("null"));
                    dto.setFamilyNameEn(personalInfo.path("FamilyNameEn").asText("null"));

                    dto.setOccupationAr(personalInfo.path("OccupationAr").asText("null"));
                    dto.setOccupationEn(personalInfo.path("OccupationEn").asText("null"));

                    dto.setEmiratesIdNumber(residence.path("EmiratesIdNumber").asText("null"));
                    dto.setIssueDate(residence.path("IssueDate").asText("null"));
                    dto.setExpiryDate(residence.path("ExpiryDate").asText("null"));

                    dto.setSponsorNameAr(residence.path("SponsorNameAr").asText("null"));
                    dto.setSponsorNameEn(residence.path("SponsorNameEn").asText("null"));

                    dto.setDocumentNo(residence.path("DocumentNo").asText("null"));

                    dto.setPersonFace(documents.path("PersonFace").asText("null"));
                    dto.setIssuePlace(immigrationFile.path("IssuePlace").asText("null"));
                    dto.setDigitalSignature(documents.path("DigitalSignature").asText("null"));
                    dto.setDigitalEID(documents.path("DigitalEID").asText("null"));



                    yield dto;
                }


                case PASSPORT -> {
                    DigitalPassportDTO dto = new DigitalPassportDTO();

                    dto.setDocumentType(passport.path("DocumentType").asText("null"));
                    dto.setDocumentNo(passport.path("DocumentNo").asText("null"));

                    dto.setDocumentNationalityAbbr(
                            passport.path("DocumentNationalityAbbr").asText("null"));
                    dto.setDocumentNationality(
                            passport.path("DocumentNationality").asText("null"));

                    dto.setPassportIssueDate(passport.path("IssueDate").asText("null"));
                    dto.setPassportExpiryDate(passport.path("ExpiryDate").asText("null"));

                    dto.setFirstNameEn(personalInfo.path("FirstNameEn").asText("null"));
                    dto.setSecondNameEn(personalInfo.path("SecondNameEn").asText("null"));

                    dto.setDateOfBirth(personalInfo.path("DateOfBirth").asText("null"));
                    dto.setGenderEn(personalInfo.path("GenderEn").asText("null"));

                    dto.setPlaceOfBirthEn(personalInfo.path("PlaceOfBirthEn").asText("null"));
                    dto.setPassportIssuePlace(
                            passport.path("DocumentIssueCountry").asText("null"));

                    dto.setPersonFace(documents.path("PersonFace").asText("null"));
                    dto.setDigitalSignature(documents.path("DigitalSignature").asText("null"));
                    dto.setPassportIssuePlace(activeVisas.path("PassportIssuePlace").asText("null"));
                    yield dto;
                }

                /* ---------------- UAEID AUTH ---------------- */
                case UAEID_AUTH -> {
                    UAEIDAuthenticationDTO dto = new UAEIDAuthenticationDTO();

                    dto.setFullNameEn(personalInfo.path("FullNameEn").asText("null"));
                    dto.setDateOfBirth(personalInfo.path("DateOfBirth").asText("null"));
                    dto.setCurrentNationality(personalInfo.path("CurrentNationality").asText("null"));
                    dto.setGenderEn(personalInfo.path("GenderEn").asText("null"));
                    dto.setOccupationEn(personalInfo.path("OccupationEn").asText("null"));

                    dto.setEmiratesIdNumber(residence.path("EmiratesIdNumber").asText("null"));
                    dto.setIssueDate(residence.path("IssueDate").asText("null"));
                    dto.setExpiryDate(residence.path("ExpiryDate").asText("null"));

                    dto.setDocumentType(passport.path("DocumentType").asText("null"));
                    dto.setDocumentNo(passport.path("DocumentNo").asText("null"));
                    dto.setDocumentNationalityAbbr(
                            passport.path("DocumentNationalityAbbr").asText("null"));
                    dto.setDocumentNationality(
                            passport.path("DocumentNationality").asText("null"));

                    dto.setPassportType(passport.path("DocumentType").asText("null"));

                    dto.setPassportIssueDate(passport.path("IssueDate").asText("null"));
                    dto.setPassportExpiryDate(passport.path("ExpiryDate").asText("null"));

                    dto.setSuid(profile.getSubscriberUid());
                    dto.setLoa(profile.getLevelOfAssurance());


                    yield dto;
                }
            };

        } catch (Exception e) {
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        }
    }


    @Override
    public ApiResponse getUserProfileDetailsBySuid(String suid) {
        try{

            if(suid ==null || suid.isEmpty()){
                return AppUtil.createApiResponse(false,"Suid cannot be null",null);

            }

            Subscriber subscriber = subscriberRepoIface.findBySubscriberUid(suid);
            if(subscriber == null){
                return AppUtil.createApiResponse(false,"Subscriber cannot be found with this suid",null);
            }
           Optional<SubscriberOnboardingData> subscriberOnboardingData = subscriberOnboardingDataRepoIface.findTopBySubscriberUidOrderBySubscriberOnboardingDataIdDesc(subscriber.getSubscriberUid());

            if(!subscriberOnboardingData.isPresent()){
                return AppUtil.createApiResponse(false,"Subscriber ob data cannot be found with this suid",null);
            }

            UserProfileResponseDTO userProfileResponseDTO = new UserProfileResponseDTO();

            String json = subscriberOnboardingData.get().getOnboardingDataFieldsJson();

            JsonNode rootNode = objectMapper.readTree(json);


            JsonNode root = objectMapper.readTree(subscriberOnboardingData.get().getNiraResponse());

            JsonNode dataNode = root
                    .path("customerDetails")
                    .path("Result")
                    .path("Data");

            JsonNode personalInfo = dataNode.path("PersonalInfo");
            JsonNode passport = dataNode.path("ActivePassport");
            JsonNode residence = dataNode.path("ResidenceInfo");
            JsonNode documents = dataNode.path("Documents");
            JsonNode sponsor = dataNode.path("SponsorDetails");
            JsonNode immigrationFile = dataNode.path("ImmigrationFile");
            JsonNode activeVisas = dataNode.path("ActiveVisa");

            userProfileResponseDTO.setSuid(suid);
            userProfileResponseDTO.setBirthdate(rootNode.path("dateOfBirth").asText("null"));
            userProfileResponseDTO.setName(subscriber.getFullName());
            userProfileResponseDTO.setPhone(subscriber.getMobileNumber());
            userProfileResponseDTO.setEmail(subscriber.getEmailId());
            userProfileResponseDTO.setGender(rootNode.path("gender").asText("null"));
            userProfileResponseDTO.setId_document_type(subscriberOnboardingData.get().getIdDocType());
            userProfileResponseDTO.setId_document_number(subscriberOnboardingData.get().getIdDocNumber());
            userProfileResponseDTO.setLoa(subscriberOnboardingData.get().getLevelOfAssurance());
            userProfileResponseDTO.setCountry(rootNode.path("nationality").asText("null"));



            userProfileResponseDTO.setFullNameEn(personalInfo.path("FullNameEn").asText("null"));
            userProfileResponseDTO.setDateOfBirth(personalInfo.path("DateOfBirth").asText("null"));
            userProfileResponseDTO.setCurrentNationality(personalInfo.path("CurrentNationality").asText("null"));
            userProfileResponseDTO.setGenderEn(personalInfo.path("GenderEn").asText("null"));
            userProfileResponseDTO.setOccupationEn(personalInfo.path("OccupationEn").asText("null"));

            userProfileResponseDTO.setEmiratesIdNumber(residence.path("EmiratesIdNumber").asText("null"));
            userProfileResponseDTO.setIssueDate(residence.path("IssueDate").asText("null"));
            userProfileResponseDTO.setExpiryDate(residence.path("ExpiryDate").asText("null"));

            userProfileResponseDTO.setPassportType(passport.path("DocumentType").asText("null"));
            userProfileResponseDTO.setPassportNo(passport.path("DocumentNo").asText("null"));
            userProfileResponseDTO.setDocumentNationalityAbbr(
                    passport.path("DocumentNationalityAbbr").asText("null"));
            userProfileResponseDTO.setDocumentNationality(
                    passport.path("DocumentNationality").asText("null"));

            userProfileResponseDTO.setPassportIssueDate(passport.path("IssueDate").asText("null"));
            userProfileResponseDTO.setPassportExpiryDate(passport.path("ExpiryDate").asText("null"));

            return AppUtil.createApiResponse(true,"Fetched Profile",userProfileResponseDTO);

        }catch (Exception e){

            e.printStackTrace();
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        }
    }




    public static String hmacSha256Base64(String data) {
        try {
            String secretKey = "DiGiTaLtRuStTeChNoLoGy";

            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec =
                    new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

            mac.init(keySpec);

            byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hmacBytes);

        } catch (Exception e) {
            throw new RuntimeException("Error while generating HMAC SHA256", e);
        }
    }


    public static boolean verifyHmac(String data, String receivedHash) {

        String computedHash = hmacSha256Base64(data);

        logger.info("=== VERIFY HMAC DEBUG ===");
        logger.info("COMPUTED HASH = [" + computedHash + "]");
        logger.info("RECEIVED HASH = [" + receivedHash + "]");
        logger.info("EQUAL STRING  = " + computedHash.equals(receivedHash));
        logger.info("========================");

        return MessageDigest.isEqual(
                computedHash.getBytes(StandardCharsets.UTF_8),
                receivedHash.getBytes(StandardCharsets.UTF_8)
        );
    }


    @Override
    public ApiResponse getUAEIDAuthenticatinProfileBySuid(String suid) {
        try{
            if(suid==null || suid.isEmpty()){
                return AppUtil.createApiResponse(false,"suid cannot be null",null);

            }

            Subscriber subscriber = subscriberRepoIface.findBySubscriberUid(suid);
            if(subscriber == null){
                return AppUtil.createApiResponse(false,"Subscriber cannot be found with this suid",null);
            }
            Optional<SubscriberOnboardingData> subscriberOnboardingData = subscriberOnboardingDataRepoIface.findTopBySubscriberUidOrderBySubscriberOnboardingDataIdDesc(subscriber.getSubscriberUid());

            if(!subscriberOnboardingData.isPresent()){
                return AppUtil.createApiResponse(false,"Subscriber ob data cannot be found with this suid",null);
            }

            UAEIDAuthenticationProfileDTO uaeidAuthenticationProfileDTO = new UAEIDAuthenticationProfileDTO();
            String json = subscriberOnboardingData.get().getOnboardingDataFieldsJson();

            JsonNode rootNode = objectMapper.readTree(json);


            JsonNode root = objectMapper.readTree(subscriberOnboardingData.get().getNiraResponse());

            JsonNode dataNode = root
                    .path("customerDetails")
                    .path("Result")
                    .path("Data");

            JsonNode personalInfo = dataNode.path("PersonalInfo");
            JsonNode passport = dataNode.path("ActivePassport");
            JsonNode residence = dataNode.path("ResidenceInfo");

            uaeidAuthenticationProfileDTO.setFullName(personalInfo.path("FullNameEn").asText("null"));
            uaeidAuthenticationProfileDTO.setNationality(rootNode.path("nationality").asText("null"));
            uaeidAuthenticationProfileDTO.setEmiratesIdNumber(residence.path("EmiratesIdNumber").asText("null"));
            uaeidAuthenticationProfileDTO.setEmiratesIdExpiryDate(residence.path("ExpiryDate").asText("null"));
            uaeidAuthenticationProfileDTO.setPassportExpiryDate(passport.path("ExpiryDate").asText("null"));
            uaeidAuthenticationProfileDTO.setPassportNumber(passport.path("DocumentNo").asText("null"));

            uaeidAuthenticationProfileDTO.setLoa(subscriberOnboardingData.get().getLevelOfAssurance());
            uaeidAuthenticationProfileDTO.setSuid(suid);

            return AppUtil.createApiResponse(true,"UAEID Authentication Profile Fetched Successfully",uaeidAuthenticationProfileDTO);





        }catch (Exception e){
            e.printStackTrace();
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        }
    }
}
