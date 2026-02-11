package com.dtt.service.impl;


import com.dtt.requestDTO.ApiResponse;
import com.dtt.model.Card;
import com.dtt.repo.CardRepo;
import com.dtt.requestDTO.CardDto;
import com.dtt.requestDTO.SubscriberDetailsDto;
import com.dtt.responseDTO.CardResponseDTO;
import com.dtt.service.iface.CardIface;
import com.dtt.utils.AppUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class CardImpl implements CardIface {
	@Autowired
	CardRepo cardRepo;

	@Value("${card.Coordinates}")
	public String cardCoordinates;
	@Value("${face.crop}")
	public String url;

	@Value("${card.expiry.years}")
	public long cardExpiryYears;

	@Value("${qr.embed.api.url}")
	private String qrEmbedApiUrl;

	@Value("${qr.embed.credentialId}")
	private String qrCredentialId;

	@Value("${card.title}")
	private String cardTitle;

	@Value("${card.title.coordinates}")
	private String cardTitleCoordinates;

	@Value("${qr.coordinates.x}")
	private String qrXCoordinate;

	@Value("${qr.coordinates.y}")
	private String qrYCoordinate;

	@Value("${card.pdf.name}")
	private String pdfCard;


	RestTemplate restTemplate = new RestTemplate();

	static final String CLASS = "PidImpl";
	Logger logger = LoggerFactory.getLogger(CardImpl.class);

	public String faceCrop(String image){

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String jsonPayload = String.format("{\"image\":\"%s\"}", image);

		HttpEntity<String> reqEntity = new HttpEntity<>(jsonPayload, headers);

		try {

			ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.POST, reqEntity, String.class);


			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(res.getBody());
			String result = rootNode.path("result").asText();


			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	public String modifyUAEIDCard(CardDto drivingLicenseDto) {
		try {


			ClassPathResource resource = new ClassPathResource(pdfCard);
			byte[] existingPdfBytes = Files.readAllBytes(Paths.get(resource.getURI()));


			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			// Load the existing PDF
			PdfReader pdfReader = new PdfReader(new ByteArrayInputStream(existingPdfBytes));
			PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
			PdfDocument pdfDocument = new PdfDocument(pdfReader, pdfWriter);

			Field[] fields = drivingLicenseDto.getClass().getDeclaredFields();

			String[] titleCoords = cardTitleCoordinates.split(",");
			float titleY = Float.parseFloat(titleCoords[1]); // keep only Y from properties

			// Get X from the photo coordinates in pidCoordinates
			JsonNode coordsNode = jsonconvertion(cardCoordinates);
			String[] photoCoords = coordsNode.get("photo").asText().split(",");
			float titleX = Float.parseFloat(photoCoords[0]); // use same X as photo

			PdfCanvas pdfCanvasTitle = new PdfCanvas(pdfDocument.getPage(1));
			Canvas titleCanvas = new Canvas(pdfCanvasTitle, pdfDocument, new Rectangle(titleX, titleY, 500, 50));

			Text titleText = new Text(cardTitle)
					.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
					.setFontSize(24)
					.setFontColor(ColorConstants.BLACK);

			Paragraph titleParagraph = new Paragraph(titleText);
			titleCanvas.add(titleParagraph);
			titleCanvas.close();

			for (Field field : fields) {
				field.setAccessible(true);
				try {


					int pageNumber = 1; // Page number where you want to add text
					PdfCanvas pdfCanvas = new PdfCanvas(pdfDocument.getPage(pageNumber));


					JsonNode jsonNode = jsonconvertion(cardCoordinates);

					String input = jsonNode.get(field.getName()).toString();


					String[] parts = input.split(",");


					float x = Float.parseFloat(parts[0].replace("\"", ""));
					float y = Float.parseFloat(parts[1].replace("\"", ""));


					Rectangle rectangle = new Rectangle(x, y, 1000, 50); // Adjust width and height as needed
					Canvas canvas = new Canvas(pdfCanvas, rectangle);


					if (field.getName().equals("photo")) {


//
						byte[] imageBytes = Base64.getDecoder().decode(drivingLicenseDto.getPhoto());
						ImageData imageData = ImageDataFactory.create(imageBytes);

						Image image = new Image(imageData);


						image.setWidth(225);

						image.setHeight(225);
						image.setFixedPosition(x, y);


						canvas.add(image);
						canvas.close();


					} else if (field.getName().equals("name")) {


						Text pdfText = new Text(((String) field.get(drivingLicenseDto)).toUpperCase())
								.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontSize(20)
								.setFontColor(ColorConstants.BLACK);


						float maxWidth = 250;

						Paragraph paragraph = new Paragraph().add(pdfText)
								.setWidth(maxWidth) // Set the maximum width
								.setMultipliedLeading(1); // Ensures proper spacing between lines

						canvas.add(paragraph);


						canvas.close();
					} else {
						Text pdfText = new Text(((String) field.get(drivingLicenseDto)).toUpperCase())
								.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontSize(20)
								.setFontColor(ColorConstants.BLACK);


						float maxWidth = 200; // Adjust this value as per your requirements

						Paragraph paragraph = new Paragraph().add(pdfText)
								.setWidth(maxWidth) // Set the maximum width
								.setMultipliedLeading(1); // Ensures proper spacing between lines

//

						canvas.add(paragraph);
					}


				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return null;
				}
			}

			pdfDocument.close();
			byte[] modifiedPdfBytes = byteArrayOutputStream.toByteArray();
			return Base64.getEncoder().encodeToString(modifiedPdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


//    public String modifyUAEIDCard(CardDto drivingLicenseDto) {
//        try {
////            String res = faceCrop(drivingLicenseDto.getPhoto());
////            if(res==null || res.isEmpty()){
////                return "";
////            }
//
//            ClassPathResource resource = new ClassPathResource(pdfCard);
//            byte[] existingPdfBytes = Files.readAllBytes(Paths.get(resource.getURI()));
//
//
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//
//            // Load the existing PDF
//            PdfReader pdfReader = new PdfReader(new ByteArrayInputStream(existingPdfBytes));
//            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
//            PdfDocument pdfDocument = new PdfDocument(pdfReader, pdfWriter);
//
//            Field[] fields = drivingLicenseDto.getClass().getDeclaredFields();
//
//                String[] titleCoords = cardTitleCoordinates.split(",");
//                float titleY = Float.parseFloat(titleCoords[1]); // keep only Y from properties
//
//                // Get X from the photo coordinates in pidCoordinates
//                JsonNode coordsNode = jsonconvertion(cardCoordinates);
//                String[] photoCoords = coordsNode.get("photo").asText().split(",");
//                float titleX = Float.parseFloat(photoCoords[0]); // use same X as photo
//
//                PdfCanvas pdfCanvasTitle = new PdfCanvas(pdfDocument.getPage(1));
//                Canvas titleCanvas = new Canvas(pdfCanvasTitle, pdfDocument, new Rectangle(titleX, titleY, 500, 50));
//
//                Text titleText = new Text(cardTitle)
//                        .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
//                        .setFontSize(24)
//                        .setFontColor(ColorConstants.BLACK);
//
//                Paragraph titleParagraph = new Paragraph(titleText);
//                titleCanvas.add(titleParagraph);
//                titleCanvas.close();
//
//            for (Field field : fields) {
//                field.setAccessible(true);
//                try {
//
//
//                    int pageNumber = 1; // Page number where you want to add text
//                    PdfCanvas pdfCanvas = new PdfCanvas(pdfDocument.getPage(pageNumber));
//
//
//                    JsonNode jsonNode = jsonconvertion(cardCoordinates);
//
//                    String input = jsonNode.get(field.getName()).toString();
//
//
//                    String[] parts = input.split(",");
//
//
//                    float x = Float.parseFloat(parts[0].replace("\"", ""));
//                    float y = Float.parseFloat(parts[1].replace("\"", ""));
//
//
//                    Rectangle rectangle = new Rectangle(x, y, 1000, 10); // Adjust width and height as needed
//                    Canvas canvas = new Canvas(pdfCanvas, rectangle);
//
//
//                    if (field.getName().equals("photo")) {
//
//
////                        byte[] imageBytes = Base64.getDecoder().decode(res);
//                        byte[] imageBytes = Base64.getDecoder().decode(drivingLicenseDto.getPhoto());
//                        ImageData imageData = ImageDataFactory.create(imageBytes);
//
//                        Image image = new Image(imageData);
//
//
//                        image.setWidth(225);
//
//                        image.setHeight(225);
//                        image.setFixedPosition(x, y);
//
//
//                        canvas.add(image);
//                        canvas.close();
//
//
//                    } else if (field.getName().equals("name")) {
//
//
//                        Text pdfText = new Text(((String) field.get(drivingLicenseDto)).toUpperCase())
//                                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
//                                .setFontSize(20)
//                                .setFontColor(ColorConstants.BLACK);
//
//
//                        float maxWidth = 250;
//
//                        Paragraph paragraph = new Paragraph().add(pdfText)
//                                .setWidth(maxWidth) // Set the maximum width
//                                .setMultipliedLeading(1); // Ensures proper spacing between lines
//
//                        canvas.add(paragraph);
//
//
//                        canvas.close();
//                    } else {
//                        Text pdfText = new Text(((String) field.get(drivingLicenseDto)).toUpperCase())
//                                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
//                                .setFontSize(20)
//                                .setFontColor(ColorConstants.BLACK);
//
//
//                        float maxWidth = 200; // Adjust this value as per your requirements
//
//                        Paragraph paragraph = new Paragraph().add(pdfText)
//                                .setWidth(maxWidth) // Set the maximum width
//                                .setMultipliedLeading(1); // Ensures proper spacing between lines
//
////                    Paragraph paragraph = new Paragraph().add(pdfText);
//
//                        canvas.add(paragraph);
//                    }
//
//
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//
//            pdfDocument.close();
//            byte[] photoBytes = Base64.getDecoder().decode(drivingLicenseDto.getPhoto());
//            byte[] croppedBytes = trimWhiteBorders(photoBytes);
//
//            ImageData imageData = ImageDataFactory.create(croppedBytes);
//
//            byte[] modifiedPdfBytes = byteArrayOutputStream.toByteArray();
//            return Base64.getEncoder().encodeToString(modifiedPdfBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//


	public String modifyUGPassCard(CardDto drivingLicenseDto) {
		try {
			String res = faceCrop(drivingLicenseDto.getPhoto());
			if(res==null || res.isEmpty()){
				return "";
			}
			ClassPathResource resource = new ClassPathResource(pdfCard);
			byte[] existingPdfBytes = Files.readAllBytes(Paths.get(resource.getURI()));


			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			// Load the existing PDF
			PdfReader pdfReader = new PdfReader(new ByteArrayInputStream(existingPdfBytes));
			PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
			PdfDocument pdfDocument = new PdfDocument(pdfReader, pdfWriter);

			Field[] fields = drivingLicenseDto.getClass().getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);
				try {


					int pageNumber = 1; // Page number where you want to add text
					PdfCanvas pdfCanvas = new PdfCanvas(pdfDocument.getPage(pageNumber));


					JsonNode jsonNode = jsonconvertion(cardCoordinates);

					String input = jsonNode.get(field.getName()).toString();


					String[] parts = input.split(",");


					float x = Float.parseFloat(parts[0].replace("\"", ""));
					float y = Float.parseFloat(parts[1].replace("\"", ""));


					Rectangle rectangle = new Rectangle(x, y, 1000, 50); // Adjust width and height as needed
					Canvas canvas = new Canvas(pdfCanvas, rectangle);


					if (field.getName().equals("photo")) {


						byte[] imageBytes = Base64.getDecoder().decode(res);
//                        byte[] imageBytes = Base64.getDecoder().decode(drivingLicenseDto.getPhoto());
						ImageData imageData = ImageDataFactory.create(imageBytes);

						Image image = new Image(imageData);


						image.setWidth(100);

						image.setHeight(100);
						image.setFixedPosition(x, y);


						canvas.add(image);
						canvas.close();


					} else if (field.getName().equals("name")) {


						Text pdfText = new Text(((String) field.get(drivingLicenseDto)).toUpperCase())
								.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontSize(12)
								.setFontColor(ColorConstants.BLACK);


						float maxWidth = 250;

						Paragraph paragraph = new Paragraph().add(pdfText)
								.setWidth(maxWidth) // Set the maximum width
								.setMultipliedLeading(1); // Ensures proper spacing between lines

						canvas.add(paragraph);


						canvas.close();
					} else {
						Text pdfText = new Text(((String) field.get(drivingLicenseDto)).toUpperCase())
								.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
								.setFontSize(12)
								.setFontColor(ColorConstants.BLACK);


						float maxWidth = 200; // Adjust this value as per your requirements

						Paragraph paragraph = new Paragraph().add(pdfText)
								.setWidth(maxWidth) // Set the maximum width
								.setMultipliedLeading(1); // Ensures proper spacing between lines

//                    Paragraph paragraph = new Paragraph().add(pdfText);

						canvas.add(paragraph);
					}


				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return null;
				}
			}

			pdfDocument.close();
			byte[] modifiedPdfBytes = byteArrayOutputStream.toByteArray();
			return Base64.getEncoder().encodeToString(modifiedPdfBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


	public JsonNode jsonconvertion(String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree(jsonString);
			return jsonNode;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


//    @Override
//    public ApiResponse getPidByIdDocNumber(String idDocNumber) {
//        try {
//
//            Card card = cardRepo.findByIdDocNumber(idDocNumber);
//            System.out.println("card response:::::::::"+card);
//
//            if (card != null) {
//
//                logger.info("pid already in DB::::", card.getFullName());
//
//                CardResponseDTO cardResponseDTO = new CardResponseDTO();
//                cardResponseDTO.setFullName(card.getFullName());
//                cardResponseDTO.setGender(card.getGender());
//                cardResponseDTO.setNationality(card.getNationality());
//
////                String combinedMobileNumber =  card.getCountryCode() + card.getMobileNumber();
//                cardResponseDTO.setMobileNumber(card.getMobileNumber());
//
//                cardResponseDTO.setEmail(card.getEmail());
//                cardResponseDTO.setAddress(card.getAddress());
//                cardResponseDTO.setIdDocNumber(card.getIdDocNumber());
//                cardResponseDTO.setCardNumber(card.getCardNumber());
//
//                cardResponseDTO.setDateOfBirth(formatDate(card.getDateOfBirth()));
//                cardResponseDTO.setPidIssueDate(formatDate(card.getPidIssueDate()));
//                cardResponseDTO.setPidExpiryDate(formatDate(card.getPidExpiryDate()));
//
//                cardResponseDTO.setPhoto(card.getPhoto());
//                cardResponseDTO.setPidDocument(card.getPidDocument());
//
//                return AppUtil.createApiResponse(true, "PID fetched successfully", cardResponseDTO);
//            } else {
//
//                logger.info("PID is not present in DB");
//
//                SubscriberDetailsDto subscriberDetailsDto = cardRepo.getSubscriberDetailsByDocumentNumber(idDocNumber);
//                System.out.println("response:::::::::::"+subscriberDetailsDto);
//                if(subscriberDetailsDto==null){
//                    return AppUtil.createApiResponse(false,"Subscriber not found",null);
//
//                }
//
//                System.out.println("Date of Birth:::"+subscriberDetailsDto.getDateOfBirth());
//                System.out.println("Mobile Number:::"+subscriberDetailsDto.getMobileNumber());
//
//                CardDto pidDto1 = new CardDto();
//                pidDto1.setName(subscriberDetailsDto.getFullName());
////                pidDto1.setDateOfBirth(formatDate(subscriberDetailsDto.getDateOfBirth()).substring(0,10));
//                pidDto1.setDateOfBirth(formatDate(subscriberDetailsDto.getDateOfBirth().substring(0,10)));
//                String createdOn = subscriberDetailsDto.getCreatedOn().substring(0, 10);
//                pidDto1.setPidIssueDate(formatDate(createdOn));
//
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//                String expiryDate = LocalDate.parse(createdOn, formatter)
//                        .plusYears(cardExpiryYears)
//                        .format(formatter);
//
//                ObjectMapper mapper = new ObjectMapper();
//
//                JsonNode rootNode = mapper.readTree(subscriberDetailsDto.getOnboardingDataFieldsJson());
//
//                String nationality = rootNode.get("nationality").asText();
//
//                System.out.println("Nationality: " + nationality);
//
//                pidDto1.setPidExpiryDate(formatDate(expiryDate));
//                pidDto1.setNationality(nationality);
//                pidDto1.setPhoto(imageToBase64(subscriberDetailsDto.getSelfieUri()));
//                pidDto1.setCardNumber(idDocNumber);
//
//                String pidcard = null;
//
//
//                if(!cardTitle.equals("UGPass CARD")){
//                    pidcard = modifyUAEIDCard(pidDto1);
//                    if(pidcard==null||pidcard.isEmpty()){
//                        return AppUtil.createApiResponse(false, "PID Card not generated Please try again", null);
//                    }
//                }
//                else{
//                    pidcard = modifyUGPassCard(pidDto1);
//                    if(pidcard==null||pidcard.isEmpty()){
//                        return AppUtil.createApiResponse(false, "PID Card not generated Please try again", null);
//                    }
//                }
//
//
//
//
//
//                Map<String, Object> qrRequest = new HashMap<>();
//                qrRequest.put("publicData", Arrays.asList(subscriberDetailsDto.getFullName(), idDocNumber));
//                qrRequest.put("privateData", Arrays.asList(
//                        subscriberDetailsDto.getFullName(),
//                        subscriberDetailsDto.getGender(),
//                        formatDate(subscriberDetailsDto.getDateOfBirth()),
//                        nationality,
//                        idDocNumber,
//                        idDocNumber,
//                        formatDate(createdOn),
//                        formatDate(expiryDate)
//                ));
//                qrRequest.put("document", pidcard);
//                qrRequest.put("portrait", imageToBase64(subscriberDetailsDto.getSelfieUri()));
//                qrRequest.put("credentialId", qrCredentialId);
//
//
//                Map<String, Object> qrCoordinates = new HashMap<>();
//                qrCoordinates.put("x", qrXCoordinate);
//                qrCoordinates.put("y", qrYCoordinate);
//                qrCoordinates.put("pageNumber", 1);
//                qrRequest.put("qrCoordinates", qrCoordinates);
//
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.APPLICATION_JSON);
//
//                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(qrRequest, headers);
//
//                logger.info(CLASS + "method Name  :: savePid()  Url of qrEmbedApiUrl ::" + qrEmbedApiUrl);
//
//                ResponseEntity<String> qrResponse = restTemplate.postForEntity(qrEmbedApiUrl, entity, String.class);
//
//                ObjectMapper objectMapper = new ObjectMapper();
//                JsonNode jsonNode = objectMapper.readTree(qrResponse.getBody());
//
//                if (jsonNode.has("success") && jsonNode.get("success").asText().equalsIgnoreCase("false")) {
//                    String errorMessage = jsonNode.has("message") ? jsonNode.get("message").asText() : "QR embedding failed";
//                    return AppUtil.createApiResponse(false, errorMessage, null);
//                }
//
//                String base64Pdf = jsonNode.get("result").asText();
//
//                Card card1 = new Card();
//                card1.setFullName(subscriberDetailsDto.getFullName());
//                card1.setDateOfBirth(subscriberDetailsDto.getDateOfBirth().substring(0,10));
//                card1.setNationality(nationality);
//                card1.setCountryCode(nationality);
//                card1.setMobileNumber(subscriberDetailsDto.getMobileNumber());
//                card1.setEmail(subscriberDetailsDto.getEmailId());
//                card1.setAddress(nationality);
//                card1.setPhoto(imageToBase64(subscriberDetailsDto.getSelfieUri()));
//                card1.setIdDocNumber(idDocNumber);
//                card1.setPidIssueDate(createdOn);
//                card1.setPidExpiryDate(expiryDate);
//                card1.setCardNumber(idDocNumber);
//
//                card1.setPidDocument(base64Pdf);
//
//                card1.setGender(subscriberDetailsDto.getGender());
//                card1.setSubscriberUid(subscriberDetailsDto.getSubscriberUid());
//                card1.setCreatedOn(AppUtil.getCurrentDate());
//                card1.setUpdatedOn(AppUtil.getCurrentDate());
//
//                cardRepo.save(card1);
//
//                logger.info("PID saved Successfully");
//
//
//                CardResponseDTO cardResponseDTO = new CardResponseDTO();
//                cardResponseDTO.setFullName(card1.getFullName());
//                cardResponseDTO.setGender(card1.getGender());
//                cardResponseDTO.setNationality(card1.getNationality());
//
//
//                cardResponseDTO.setMobileNumber(card1.getMobileNumber());
//
//                cardResponseDTO.setEmail(card1.getEmail());
//                cardResponseDTO.setAddress(card1.getAddress());
//                cardResponseDTO.setIdDocNumber(idDocNumber);
//                cardResponseDTO.setCardNumber(idDocNumber);
//
//
//                cardResponseDTO.setDateOfBirth(formatDate(card1.getDateOfBirth()));
//                cardResponseDTO.setPidIssueDate(formatDate(card1.getPidIssueDate()));
//                cardResponseDTO.setPidExpiryDate(formatDate(card1.getPidExpiryDate()));
//
//                cardResponseDTO.setPhoto(card1.getPhoto());
//                cardResponseDTO.setPidDocument(base64Pdf);
//                return AppUtil.createApiResponse(true, "PID fetched successfully", cardResponseDTO);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return AppUtil.createApiResponse(false, "Something went wrong", null);
//        }
//    }


	private String formatDate(String date) {
		try {
			SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date parsedDate = originalFormat.parse(date);
			return targetFormat.format(parsedDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
	}





	public String imageToBase64(String imageUrl) {

		try {


			HttpHeaders headers = new HttpHeaders();


			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<byte[]> response = restTemplate.exchange(
					imageUrl,
					HttpMethod.GET,
					entity,
					byte[].class
			);

			if (response.getStatusCode().is2xxSuccessful()) {
				byte[] imageBytes = response.getBody();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				return base64Image;
			} else {
				System.out.println("Image not found");
				return "";
			}
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}

	}


	@Override
	public ApiResponse getPidByIdDocNumber(String idDocNumber) {
		try {

			Card card = cardRepo.findByIdDocNumber(idDocNumber);
			System.out.println("card response:::::::::"+card);

			if (card != null) {

				logger.info("pid already in DB::::", card.getFullName());

				CardResponseDTO cardResponseDTO = new CardResponseDTO();
				cardResponseDTO.setFullName(card.getFullName());
				cardResponseDTO.setGender(card.getGender());
				cardResponseDTO.setNationality(card.getNationality());

//                String combinedMobileNumber =  card.getCountryCode() + card.getMobileNumber();
				cardResponseDTO.setMobileNumber(card.getMobileNumber());

				cardResponseDTO.setEmail(card.getEmail());
				cardResponseDTO.setAddress(card.getAddress());
				cardResponseDTO.setIdDocNumber(card.getIdDocNumber());
				cardResponseDTO.setCardNumber(card.getCardNumber());

				cardResponseDTO.setDateOfBirth(formatDate(card.getDateOfBirth()));
				cardResponseDTO.setPidIssueDate(formatDate(card.getPidIssueDate()));
				cardResponseDTO.setPidExpiryDate(formatDate(card.getPidExpiryDate()));

				cardResponseDTO.setPhoto(card.getPhoto());
//                cardResponseDTO.setPidDocument(card.getPidDocument());

				return AppUtil.createApiResponse(true, "PID fetched successfully", cardResponseDTO);
			} else {

				logger.info("PID is not present in DB");

				SubscriberDetailsDto subscriberDetailsDto = cardRepo.getSubscriberDetailsByDocumentNumber(idDocNumber);
				System.out.println("response:::::::::::"+subscriberDetailsDto);
				if(subscriberDetailsDto==null){
					return AppUtil.createApiResponse(false,"Subscriber not found",null);

				}

				System.out.println("Date of Birth:::"+subscriberDetailsDto.getDateOfBirth());
				System.out.println("Mobile Number:::"+subscriberDetailsDto.getMobileNumber());

				CardDto pidDto1 = new CardDto();
				pidDto1.setName(subscriberDetailsDto.getFullName());
//                pidDto1.setDateOfBirth(formatDate(subscriberDetailsDto.getDateOfBirth()).substring(0,10));
				pidDto1.setDateOfBirth(formatDate(subscriberDetailsDto.getDateOfBirth().substring(0,10)));
				String createdOn = subscriberDetailsDto.getCreatedOn().substring(0, 10);
				pidDto1.setPidIssueDate(formatDate(createdOn));

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				String expiryDate = LocalDate.parse(createdOn, formatter)
						.plusYears(cardExpiryYears)
						.format(formatter);

				ObjectMapper mapper = new ObjectMapper();

				JsonNode rootNode = mapper.readTree(subscriberDetailsDto.getOnboardingDataFieldsJson());

				String nationality = rootNode.get("nationality").asText();

				System.out.println("Nationality: " + nationality);

				pidDto1.setPidExpiryDate(formatDate(expiryDate));
				pidDto1.setNationality(nationality);
				pidDto1.setPhoto(subscriberDetailsDto.getSelfie());
				pidDto1.setCardNumber(idDocNumber);








				Card card1 = new Card();
				card1.setFullName(subscriberDetailsDto.getFullName());
				card1.setDateOfBirth(subscriberDetailsDto.getDateOfBirth().substring(0,10));
				card1.setNationality(nationality);
				card1.setCountryCode(nationality);
				card1.setMobileNumber(subscriberDetailsDto.getMobileNumber());
				card1.setEmail(subscriberDetailsDto.getEmailId());
				card1.setAddress(nationality);
				card1.setPhoto(subscriberDetailsDto.getSelfie());
				card1.setIdDocNumber(idDocNumber);
				card1.setPidIssueDate(createdOn);
				card1.setPidExpiryDate(expiryDate);
				card1.setCardNumber(idDocNumber);

				card1.setPidDocument(null);

				card1.setGender(subscriberDetailsDto.getGender());
				card1.setSubscriberUid(subscriberDetailsDto.getSubscriberUid());
				card1.setCreatedOn(AppUtil.getCurrentDate());
				card1.setUpdatedOn(AppUtil.getCurrentDate());

				cardRepo.save(card1);

				logger.info("PID saved Successfully");


				CardResponseDTO cardResponseDTO = new CardResponseDTO();
				cardResponseDTO.setFullName(card1.getFullName());
				cardResponseDTO.setGender(card1.getGender());
				cardResponseDTO.setNationality(card1.getNationality());


				cardResponseDTO.setMobileNumber(card1.getMobileNumber());

				cardResponseDTO.setEmail(card1.getEmail());
				cardResponseDTO.setAddress(card1.getAddress());
				cardResponseDTO.setIdDocNumber(idDocNumber);
				cardResponseDTO.setCardNumber(idDocNumber);


				cardResponseDTO.setDateOfBirth(formatDate(card1.getDateOfBirth()));
				cardResponseDTO.setPidIssueDate(formatDate(card1.getPidIssueDate()));
				cardResponseDTO.setPidExpiryDate(formatDate(card1.getPidExpiryDate()));

				cardResponseDTO.setPhoto(card1.getPhoto());

				return AppUtil.createApiResponse(true, "PID fetched successfully", cardResponseDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AppUtil.createApiResponse(false, "Something went wrong", null);
		}
	}




}
